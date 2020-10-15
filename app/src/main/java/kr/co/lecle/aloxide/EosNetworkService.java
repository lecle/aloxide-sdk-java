package kr.co.lecle.aloxide;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.jafka.jeos.EosApi;
import io.jafka.jeos.EosApiFactory;
import io.jafka.jeos.convert.Packer;
import io.jafka.jeos.core.common.SignArg;
import io.jafka.jeos.core.common.transaction.PackedTransaction;
import io.jafka.jeos.core.common.transaction.TransactionAction;
import io.jafka.jeos.core.common.transaction.TransactionAuthorization;
import io.jafka.jeos.core.request.chain.transaction.PushTransactionRequest;
import io.jafka.jeos.core.response.chain.AbiJsonToBin;
import io.jafka.jeos.core.response.chain.TableRow;
import io.jafka.jeos.core.response.chain.transaction.PushedTransaction;
import io.jafka.jeos.impl.EosApiServiceGenerator;
import io.jafka.jeos.impl.EosChainApiService;
import io.jafka.jeos.util.KeyUtil;
import io.jafka.jeos.util.Raw;

/**
 * Created by quocb14005xx on 12,October,2020
 * "https://testnet.canfoundation.io"
 */
class EosNetworkService extends BlockchainNetwork {
    private String enityName;
    private String contract;
    private String url;
    private BlockchainAccount account;
    private EosApi eosApi;

    EosNetworkService(String entityName, BlockchainAccount account, String contract, String url) {
        this.enityName = entityName.toLowerCase();
        this.account = account;
        this.contract = contract;
        this.url = url;
        eosApi = EosApiFactory.create(this.url);
    }


    /**
     *
     * @param id
     * @return Map{}
     */
    @Override
    public Object get(Object id) {
        assert this.contract != null;
        assert this.account != null;
        assert this.enityName != null;
        assert eosApi != null;
        TableRow result = getTableRows(this.account.getName(), this.contract, this.enityName,
                id.toString(), id.toString(), "1");

        return result.getRows().get(0);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Object add(Object params) {
        String methodName = "cre" + this.enityName;
        String privateKey = this.account.getPrivateKey();
        String from = this.account.getName();
        AbiJsonToBin data = eosApi.abiJsonToBin(this.account.getName(), methodName, params);
        return sendTransaction(from, methodName, data.getBinargs(), privateKey);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String sendTransaction(String from, String action, String transferData, String privateKey) {
        SignArg arg = eosApi.getSignArg(120);

        // ③ create the authorization
        List<TransactionAuthorization> authorizations = Collections.singletonList(new TransactionAuthorization(from, "active"));

        // ④ build the all actions
        List<TransactionAction> actions = Collections.singletonList(//
                new TransactionAction(this.account.getName(), action, authorizations, transferData)//
        );

        // ⑤ build the packed transaction
        PackedTransaction packedTransaction = new PackedTransaction();
        packedTransaction.setExpiration(arg.getHeadBlockTime().plusSeconds(arg.getExpiredSecond()));
        packedTransaction.setRefBlockNum(arg.getLastIrreversibleBlockNum());
        packedTransaction.setRefBlockPrefix(arg.getRefBlockPrefix());

        packedTransaction.setMaxNetUsageWords(0);
        packedTransaction.setMaxCpuUsageMs(0);
        packedTransaction.setDelaySec(0);
        packedTransaction.setActions(actions);

        String hash = sign(privateKey, arg, packedTransaction);
        PushTransactionRequest req = new PushTransactionRequest();
        req.setTransaction(packedTransaction);
        req.setSignatures(Collections.singletonList(hash));

        PushedTransaction pts = eosApi.pushTransaction(req);
        return pts.getTransactionId();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Object update(String id, Object params) {
        String methodName = "upd" + this.enityName;
        String privateKey = this.account.getPrivateKey();
        String from = this.account.getName();
        AbiJsonToBin data = eosApi.abiJsonToBin(this.account.getName(), methodName, params);

        return sendTransaction(from, methodName, data.getBinargs(), privateKey);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Object delete(String id) {
        String methodName = "del" + this.enityName;
        String privateKey = this.account.getPrivateKey();
        String from = this.account.getName();
        Map<String, String> d = new HashMap<>(2);
        d.put("id", id);
        d.put("user", this.account.getName());
        AbiJsonToBin data = eosApi.abiJsonToBin(this.account.getName(), methodName, d);
        return sendTransaction(from, methodName, data.getBinargs(), privateKey);
    }

    /**
     * Local Sign Transaction
     * @param privateKey
     * @param arg
     * @param t
     * @return
     */
    private String sign(String privateKey, SignArg arg, PackedTransaction t) {
        Raw raw = Packer.packPackedTransaction(arg.getChainId(), t);
        raw.pack(ByteBuffer.allocate(33).array());
        return KeyUtil.signHash(privateKey, raw.bytes());
    }

    /**
     *
     * @param scope
     * @param code
     * @param table
     * @param lower_bound
     * @param upper_bound
     * @param limit
     * @return
     */
    TableRow getTableRows(String scope, String code, String table, String lower_bound, String upper_bound, String limit) {
        LinkedHashMap<String, String> requestParameters = new LinkedHashMap<>(7);

        requestParameters.put("scope", scope);
        requestParameters.put("code", code);
        requestParameters.put("table", table);
        requestParameters.put("lower_bound", lower_bound);
        requestParameters.put("upper_bound", upper_bound);
        requestParameters.put("limit", limit);
        requestParameters.put("json", "true");
        EosChainApiService eosChainApiService = EosApiServiceGenerator.createService(EosChainApiService.class, this.url);

        return EosApiServiceGenerator.executeSync(eosChainApiService.getTableRows(requestParameters));
    }
}
