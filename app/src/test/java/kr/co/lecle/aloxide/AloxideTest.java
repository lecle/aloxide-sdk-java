package kr.co.lecle.aloxide;

import org.junit.Test;

import kr.co.lecle.aloxide.model.BlockchainAccount;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AloxideTest {
    @Test
    public void initAloxide() {
        BlockchainAccount bcAccount = new BlockchainAccount.BlockchainAccountBuilder()
                .setName("aloxidejs123")
                .setPrivateKey("5JHQ3GuzcQtEQgG3SGvtDU7v2b7ioKznYBizA1V5mBUUsLNcXdQ")
                .build();

        Aloxide aloxide = AloxideBuilder.newBuilder()
                .setNetwork(Network.EOS)
                .setUrl("https://testnet.canfoundation.io")
                .setBlockchainAccount(bcAccount)
                .setEnityName("Poll")
                .setContract("aloxidejs123")
                .build();
        assert aloxide != null;
    }

    @Test
    public void create_aloxide_with_eos_network() {
        BlockchainAccount bcAccount = new BlockchainAccount.BlockchainAccountBuilder()
                .setName("aloxidejs123")
                .setPrivateKey("5JHQ3GuzcQtEQgG3SGvtDU7v2b7ioKznYBizA1V5mBUUsLNcXdQ")
                .build();

        Aloxide aloxide = AloxideBuilder.newBuilder()
                .setNetwork(Network.EOS)
                .setUrl("https://testnet.canfoundation.io")
                .setBlockchainAccount(bcAccount)
                .setEnityName("Poll")
                .setContract("aloxidejs123")
                .build();
        assertNotNull(aloxide);
        assertNotNull(aloxide.aloxideData);
        assertNotNull(aloxide.aloxideData.getNetwork());
        assertSame(aloxide.aloxideData.getNetwork(), Network.EOS);
        assertNotNull(aloxide.aloxideData.getBlockchainAccount());
        assertNotNull(aloxide.aloxideData.getBlockchainAccount().getName());
        assertNotNull(aloxide.aloxideData.getBlockchainAccount().getPrivateKey());

    }

    @Test
    public void create_aloxide_with_icon_network() {

    }

    @Test
    public void get_a_record_in_eos() {
        //id
    }

    @Test
    public void get_a_record_in_icon() {
        //id
    }

    @Test
    public void add_a_record_in_eos() {
        //Object
    }

    @Test
    public void add_a_record_in_icon() {
        //Object
    }
}