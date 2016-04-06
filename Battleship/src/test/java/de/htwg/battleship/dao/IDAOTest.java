package de.htwg.battleship.dao;

/**
 * IDAOTest
 *
 * @author ms
 * @since 2016-03-30
 */
public class IDAOTest {
//
//    private static final String player1Name = "PLAYER_ONE";
//    private static final String player2Name = "PLAYER_TWO";
//
//    private IDAO idao;
//    private IMasterController iMasterController;
//    private State savedState;
//
//    @Before
//    public void setUp() {
//        Injector injector = Guice.createInjector(new BattleshipModule());
//        idao = injector.getInstance(IDAO.class);
//        iMasterController = injector.getInstance(IMasterController.class);
//        iMasterController.getPlayer1Name().setProfile(player1Name);
//        iMasterController.getPlayer2Name().setProfile(player2Name);
//        savedState = iMasterController.getCurrentState();
//        idao.saveOrUpdateGame(iMasterController);
//    }

//    @Test
//    public void isGameExisting() {
//        assertTrue(idao.isGameExisting(iMasterController.getPlayer1Name(),
//                                       iMasterController.getPlayer2Name()));
//        assertTrue(idao.isGameExisting(iMasterController.getPlayer2Name(),
//                                       iMasterController.getPlayer1Name()));
//    }
//
//    @Test
//    public void listAllGames() {
//        List<IMasterController> list =
//            idao.listAllGames(iMasterController.getPlayer1Name());
//        assertEquals(1, list.size());
//        IMasterController tmp1 = list.get(list.size() - 1);
//        assertEquals(savedState, tmp1.getCurrentState());
//        list = idao.listAllGames(iMasterController.getPlayer2Name());
//        assertEquals(1, list.size());
//        IMasterController tmp2 = list.get(list.size() - 1);
//        assertEquals(savedState, tmp2.getCurrentState());
//        assertEquals(tmp1.getPlayer1Name(), tmp2.getPlayer1Name());
//        assertEquals(tmp1.getPlayer2Name(), tmp2.getPlayer2Name());
//        assertEquals(tmp1.getCurrentState(), tmp2.getCurrentState());
//    }
//
//    @Test
//    public void loadGameRight() {
//        IMasterController mc = idao.loadGame(iMasterController.getPlayer1Name(),
//                                             iMasterController.getPlayer2Name());
//        assertEquals(iMasterController.getPlayer1Name(), mc.getPlayer1Name());
//        assertEquals(iMasterController.getPlayer2Name(), mc.getPlayer2Name());
//    }
//
//    @Test
//    public void loadGameReverse() {
//        IMasterController mc = idao.loadGame(iMasterController.getPlayer2Name(),
//                                             iMasterController.getPlayer1Name());
//        assertEquals(iMasterController.getPlayer1Name(), mc.getPlayer1Name());
//        assertEquals(iMasterController.getPlayer2Name(), mc.getPlayer2Name());
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//
//    }
}
