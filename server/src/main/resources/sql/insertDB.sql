-- Records of user
INSERT INTO `user` VALUES ('1', '1', 'James Bond', '123', 'james@vt.edu', '571-000-0000', null, null, '000000', '1', '2022-11-15 15:00:00', '2022-11-14 15:00:00', '2022-11-14 15:00:00');
INSERT INTO `user` VALUES ('2', null, 'Walter White', '123', 'walter@vt.edu', '571-000-0001', null, null, '000000', '1', '2022-11-15 15:00:10',  '2022-11-14 15:00:10', '2022-11-14 15:00:10');
INSERT INTO `user` VALUES ('3', null, 'User001', '123456', 'jiayuez@vt.edu', null, null, null, '000000', '1', '2022-11-24 22:14:23', '2022-11-23 22:14:23', '2022-11-23 22:14:23');

-- Record of car_info
INSERT INTO `car_info` VALUES ('1', 'VA-001', '1', 'Ford Escape', '5', '2013', 'https://cdn.pixabay.com/photo/2018/01/19/20/56/truck-3093197_960_720.png', '2022-11-14 15:01:00', '2022-11-14 15:01:00');

-- Record of post
INSERT INTO `post` VALUES ('1', '2022-11-14 15:01:00', '2022-11-14 15:01:00', '2022-11-15 08:01:00', '2022-11-15 11:01:00', '1', '2', '200.00', '1', '0', '0');
INSERT INTO `post` VALUES ('2', '2022-10-14 15:01:00', '2022-10-14 15:01:00', '2022-10-15 08:01:00', '2022-10-15 11:01:00', '2', '3', '400.00', '2', '0', '1');

-- Record of area
INSERT INTO `area` VALUES ('1', 'On Campus', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `area` VALUES ('2', 'Off Campus', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `area` VALUES ('3', 'Downtown', '2022-11-14 17:00:00', '2022-11-14 17:00:00');

-- Record of spot
INSERT INTO `spot` VALUES ('1', 'Newman Library', '37.2288', '-80.4195', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `spot` VALUES ('2', 'McComas Hall', '37.2206', '-80.4224', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `spot` VALUES ('3', 'Whittemore Hall', '37.2310', '-80.4244', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `spot` VALUES ('4', 'Northern Virginia Center', '38.8967', '-77.1894', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `spot` VALUES ('5', 'Duckpond Road Lot', '37.2208', '-80.4287', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `spot` VALUES ('6', 'Blacksburg Downtown', '37.2292', '-80.4139', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `spot` VALUES ('7', 'Smith''s Landing Apartments', '37.2241', '-80.4418', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `spot` VALUES ('8', 'Food Lion(801 Hethwood Blvd)', '37.217020', '-80.454640', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `spot` VALUES ('9', 'Food Lion(1413 N Main St)', '37.22957', '-80.41394', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `spot` VALUES ('10', 'Kroger(1322 S Main St)', '37.2167551', '-80.402732', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `spot` VALUES ('11', 'Kroger(903 University City Blvd)', '37.2355872', '-80.4351532', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `spot` VALUES ('12', 'Eaves Fairfax Towers', '38.9005483', '-77.20452', '2022-11-14 17:00:00', '2022-11-14 17:00:00');

-- Record of Order
INSERT INTO `order` VALUES ('1', '2022-11-25 15:00:00', '2022-11-25 15:00:00', '1', '2', '1', '20.00', '1', '0');
INSERT INTO `order` VALUES ('2', '2022-11-25 16:00:00', '2022-11-25 16:00:00', '2', '1', '1', '50.00', '1', '0');

-- Record of area_spot_map
INSERT INTO `area_spot_map` VALUES ('1', '1', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `area_spot_map` VALUES ('1', '2', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `area_spot_map` VALUES ('1', '3', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `area_spot_map` VALUES ('1', '4', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `area_spot_map` VALUES ('1', '5', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `area_spot_map` VALUES ('3', '6', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `area_spot_map` VALUES ('2', '7', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `area_spot_map` VALUES ('2', '8', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `area_spot_map` VALUES ('2', '9', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `area_spot_map` VALUES ('2', '10', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `area_spot_map` VALUES ('2', '11', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `area_spot_map` VALUES ('2', '12', '2022-11-14 17:00:00', '2022-11-14 17:00:00');

-- Record of post_spot_map
INSERT INTO `post_spot_map` VALUES ('1', '1', '0', '2022-11-14 17:00:00', '2022-11-14 17:00:00');
INSERT INTO `post_spot_map` VALUES ('1', '2', '1', '2022-11-14 17:00:00', '2022-11-14 17:00:00');

-- Record of room
INSERT INTO `room` VALUES ('4', 'Luke','1234', 'xu', '321', 'wu');

-- Record of message
INSERT INTO `message` VALUES ('0', '4', '1234', 'okay', '2022-11-14 17:00:00', '0');
INSERT INTO `message` VALUES ('0', '4', '1234', 'Is 5pm okay for you?', '2022-11-14 17:01:00', '0');
