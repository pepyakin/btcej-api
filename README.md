BtceJ API - btc-e.com API для Java
==================================

Реализация API для биржи btc-e. Код лицензирован под [ZLib](http://ru.wikipedia.org/wiki/Лицензия_zlib).

Пример использования
--------------------

```java
Btce btce = Btce.getBtce(
  "AAAAAAAA-BBBBBBBB-CCCCCCCC-DDDDDDDD-EEEEEEEE",
  "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef");

TransactionHistory history = btce.getTransactionHistory().execute();
OrderHistory orderHistory = btce.orderHistory().
               count(2).
               onlyActive(false).
               execute();

TradeAnswer answer = btce.trade().buy().pair(Pair.BTC_USD).rate(10).amount(0.1).execute();
btce.cancelOrder(answer.getOrderId());
```

Copyright © [ideasium.com](http://ideasium.com/) 2012
