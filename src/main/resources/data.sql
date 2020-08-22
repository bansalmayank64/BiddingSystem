
insert into user(user_id , email, first_name, last_name) values('U11239', 'stefcohen@yahoo.com', 'stefhen','cohen');
insert into user(user_id , email, first_name, last_name) values('U11240', 'steph.pragier@gmail.com', 'stephnie','pragier');
insert into user(user_id , email, first_name, last_name) values('U11241', 'sanjabranbenz@gmail.com', 'sanja','branbenz');
insert into user(user_id , email, first_name, last_name) values('U11244', 'kaushikmanoj95@gmail.com', 'manoj','kaushik');
insert into user(user_id , email, first_name, last_name) values('U11245', 'deepaksingh@gmail.com', 'deepak','singh');
insert into user(user_id , email, first_name, last_name) values('U11246', 'ashutosh.jain12@gmail.com', 'ashutosh','jain');


insert into item(item_code ,description , name) values ('TT123ki98011', 'four wheeler', 'TataTiago');
insert into item(item_code ,description , name) values ('VKh128901111', 'Villa with 3700sqrft', 'Villa');
insert into item(item_code ,description , name) values ('Res781623', 'Resort', 'Maven Resort');
insert into item(item_code ,description , name) values ('BT8972khi123', 'Private boat cruise with cocktails', 'Adventure of seas');
insert into item(item_code ,description , name) values ('HTK718adg1123', 'Hotel & Resort', 'Malibu Hotel');


insert into auction(auction_id , auction_status,  minimum_base_price , step_rate, item_code) values('AUC22082020', 'RUNNING', 500000, 10000, 'TT123ki98011');
insert into auction(auction_id , auction_status,  minimum_base_price , step_rate, item_code) values('AUC20072020', 'RUNNING', 10000000, 50000, 'VKh128901111');
insert into auction(auction_id , auction_status,  minimum_base_price , step_rate, item_code) values('AUC22020982', 'OVER', 400000, 15000, 'Res781623');
insert into auction(auction_id , auction_status,  minimum_base_price , step_rate, item_code) values('AUC21256780', 'OVER', 1500000, 35000, 'BT8972khi123');
insert into auction(auction_id , auction_status,  minimum_base_price , step_rate, item_code) values('AUC23452671', 'RUNNING', 500000, 12000, 'HTK718adg1123');


insert into user_bid(bid_id,amount ,is_accepted,auction_id ,user_id)  values('BID001292378', 550000, 0,'AUC22082020','U11239');
insert into user_bid(bid_id,amount ,is_accepted,auction_id ,user_id) values('BID001292348', 10200000, 0 ,'AUC20072020','U11239');
insert into user_bid(bid_id,amount ,is_accepted,auction_id ,user_id) values('BID001223458', 600000, 1,'AUC22020982','U11239');
insert into user_bid(bid_id,amount ,is_accepted,auction_id ,user_id) values('BID001223478', 750000, 1,'AUC22082020','U11244');
insert into user_bid(bid_id,amount ,is_accepted,auction_id ,user_id) values('BID001220987', 650000, 0,'AUC23452671','U11244');
insert into user_bid(bid_id,amount ,is_accepted,auction_id ,user_id) values('BID001109278', 600000, 0,'AUC23452671','U11246');
insert into user_bid(bid_id,amount ,is_accepted,auction_id ,user_id) values('BID001324561', 2500000, 1, 'AUC21256780','U11240');
insert into user_bid(bid_id,amount ,is_accepted,auction_id ,user_id) values('BID001878901', 580000, 1, 'AUC22020982', 'U11240');
