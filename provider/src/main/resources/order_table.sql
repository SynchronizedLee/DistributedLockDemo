CREATE TABLE `order` (
  `order_id` int(11) NOT NULL DEFAULT '0' COMMENT '订单id',
  `order_state` int(11) DEFAULT NULL COMMENT '订单状态，0是未被抢，1是已被抢',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;