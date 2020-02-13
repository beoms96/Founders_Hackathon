package com.android.samsung.codelab.guestbookdapp.remote;

import com.android.samsung.codelab.guestbookdapp.ethereum.FunctionUtil;
import com.android.samsung.codelab.guestbookdapp.model.Feed;
import com.android.samsung.codelab.guestbookdapp.util.AppExecutors;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FeedLoader {
    private String address;

    public FeedLoader(String address) {
        this.address = address;
    }

    public void loadFeeds(int numOfFeed, Listener listener) {
        AppExecutors.getInstance().networkIO().execute(() -> {
            try {
                int postCount = getPostCount();
                int lastIndex = postCount - 1 - numOfFeed;
                if (lastIndex < 0)
                    lastIndex = 0;

                ArrayList<Feed> feedList = new ArrayList<>();
                for (int index = postCount - 1; index >= lastIndex; index--) {
                    feedList.add(getPost(index));
                }
                listener.feedDidLoaded(true, feedList, "Success");
            } catch (Exception e) {
                listener.feedDidLoaded(false, null, e.getMessage());
            }
        });

    }

    private int getPostCount() throws Exception {
        Function functionGetPostCount = FunctionUtil.createGetPostCountSmartContractCall();
        String data = FunctionEncoder.encode(functionGetPostCount);
        Transaction tx = Transaction.createEthCallTransaction(address, FunctionUtil.CONTRACT_ADDRESS, data);

        EthCall result = RemoteManager.getInstance().sendEthCall(tx);
        if (result.hasError()) {
            throw new Exception("Get Post count eth call error" + result.getError().getMessage());
        }

        String value = result.getValue();
        List<TypeReference<Type>> outputParameters = functionGetPostCount.getOutputParameters();
        List<Type> types = FunctionReturnDecoder.decode(value, outputParameters);
        Type type = types.get(0);
        BigInteger postCount = (BigInteger) type.getValue();

        return postCount.intValue();
    }

    private Feed getPost(int index) throws Exception {
        Function functionGetPost = FunctionUtil.createGetPostSmartContractCall(index);
        String data = FunctionEncoder.encode(functionGetPost);
        Transaction tx = Transaction.createEthCallTransaction(address, FunctionUtil.CONTRACT_ADDRESS, data);

        EthCall result = RemoteManager.getInstance().sendEthCall(tx);
        if (result.hasError()) {
            throw new Exception("Get Post eth call error" + result.getError().getMessage());
        }

        String value = result.getValue();
        List<TypeReference<Type>> outputParameters = functionGetPost.getOutputParameters();
        List<Type> types = FunctionReturnDecoder.decode(value, outputParameters);
        return new Feed(
                (String) types.get(3).getValue()
                , (String) types.get(0).getValue()
                , (String) types.get(1).getValue()
                , (String) types.get(2).getValue()
        );

    }

    public interface Listener {
        void feedDidLoaded(boolean success, List<Feed> feedList, String message);
    }
}
