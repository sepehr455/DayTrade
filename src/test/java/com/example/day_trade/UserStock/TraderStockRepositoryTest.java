package com.example.day_trade.UserStock;

//@DataJpaTest
//class TraderStockRepositoryTest {
//
//    @Autowired
//    private TraderRepository traderRepository;
//    @Autowired
//    private StockRepository stockRepository;
//    @Autowired
//    private TraderStockRepository traderStockRepository;
//
//    @Test
//    void stockQuantityAdderWorks() {
//
//        Trader testTrader = new Trader("sepehr", 22);
//        Stock testStock = new Stock( "testStock", 22);
//
//        traderRepository.save(testTrader);
//        stockRepository.save(testStock);
//
//        Long traderId = testTrader.getUserId();
//        Long stockId = testStock.getStock_id();
//
//        TraderStock testTraderStock = new TraderStock(testTrader, testStock, 3);
//        traderStockRepository.save(testTraderStock);
//
//
//    }
//}