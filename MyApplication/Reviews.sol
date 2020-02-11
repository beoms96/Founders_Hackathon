pragma solidity ^0.5.10;


contract Reviews {
    struct Vote {
        uint voteCount; // 전체 투표 수
        uint approve;   // 찬성표
        bool isVoting;  //투표 중 체크
    }

    struct Review {
        uint reviewId;  // 리뷰 고유 번호
        string userId;  // 리뷰 사용자
        string storeName; //가게 이름
        string comment; // 리뷰 내용
        string date;    // 날짜
        Vote vote;  //투표 정보
    }

    // 전체 리뷰
    Review[] reviews;

    //투표에 참여한 ID 기록
    mapping(address => uint) public voters;


    function getReviewsCount() public view returns(uint) {
        return reviews.length;
    }

    function review(string memory _userId, string memory _storeName, string memory _comment, string memory _date) public {
        reviews.length++;
        reviews[reviews.length-1].reviewId = reviews.length-1;
        reviews[reviews.length-1].userId = _userId;
        reviews[reviews.length-1].storeName = _storeName;
        reviews[reviews.length-1].comment = _comment;
        reviews[reviews.length-1].date = _date;
        reviews[reviews.length-1].vote.voteCount = 0;
        reviews[reviews.length-1].vote.approve = 0;
        reviews[reviews.length-1].vote.isVoting = true;
    }

    // 찬성표 투표하기
    function vote(uint _reviewId, bool _approve) public {
        voters[msg.sender] = _reviewId;
        if(_approve) {
            reviews[_reviewId].vote.voteCount += 1;
            reviews[_reviewId].vote.approve += 1;
        }
        else {
            reviews[_reviewId].vote.voteCount += 1;
        }

    }

    function terminateVoting(uint index) public {
        reviews[index].vote.isVoting = false;
    }

    function getReview(uint index) public view returns(string memory, string memory, string memory, string memory) {
        return (reviews[index].userId, reviews[index].storeName, reviews[index].comment, reviews[index].date);
    }
}
