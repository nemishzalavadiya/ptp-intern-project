/** asset Data **/

INSERT INTO public.asset_detail(id, about, asset_class, logo_url, managing_director, name, organization)
VALUES ('51381618-1bc9-4c19-aab9-44994433b181', 'about', 'STOCK', 'logo_url', 'nemish', 'ptp1', 'org1');

INSERT INTO public.asset_detail(id, about, asset_class, logo_url, managing_director, name, organization)
VALUES ('51381618-1bc9-4c19-aab9-44994433b182', 'about', 'STOCK', 'logo_url', 'harsh', 'ptp2', 'org2');

INSERT INTO public.asset_detail(id, about, asset_class, logo_url, managing_director, name, organization)
VALUES ('51381618-1bc9-4c19-aab9-44994433b183', 'about', 'STOCK', 'logo_url', 'harsh', 'ptp3', 'org3');

INSERT INTO public.asset_detail(id, about, asset_class, logo_url, managing_director, name, organization)
VALUES ('51381618-1bc9-4c19-aab9-44994433b184', 'about', 'STOCK', 'logo_url', 'Mohit', 'ptp4', 'org4');

INSERT INTO public.asset_detail(id, about, asset_class, logo_url, managing_director, name, organization)
VALUES ('51381618-1bc9-4c19-aab9-44994433b185', 'about', 'MUTUAL_FUND', 'logo_url', 'devesh', 'ptp5', 'org5');

INSERT INTO public.asset_detail(id, about, asset_class, logo_url, managing_director, name, organization)
VALUES ('51381618-1bc9-4c19-aab9-44994433b186', 'about', 'MUTUAL_FUND', 'logo_url', 'Mohit', 'ptp6', 'org6');

INSERT INTO public.asset_detail(id, about, asset_class, logo_url, managing_director, name, organization)
VALUES ('51381618-1bc9-4c19-aab9-44994433b187', 'about', 'MUTUAL_FUND', 'logo_url', 'devesh', 'ptp7', 'org7');

INSERT INTO public.asset_detail(id, about, asset_class, logo_url, managing_director, name, organization)
VALUES ('51381618-1bc9-4c19-aab9-44994433b188', 'about', 'MUTUAL_FUND', 'logo_url', 'Mohit', 'ptp8', 'org8');


/** Stock Data **/

INSERT INTO public.stock_detail(id, managing_director, year_founded, asset_detail_id)
VALUES ('2ffedff5-70c5-45cd-9c35-b36c25d77361', 'dirij', '2019-01-21T05:47:08.644',
        '51381618-1bc9-4c19-aab9-44994433b181');

INSERT INTO public.stock_detail(id, managing_director, year_founded, asset_detail_id)
VALUES ('2ffedff5-70c5-45cd-9c35-b36c25d77362', 'nemish', '2020-01-21T05:47:08.644',
        '51381618-1bc9-4c19-aab9-44994433b182');

INSERT INTO public.stock_detail(id, managing_director, year_founded, asset_detail_id)
VALUES ('2ffedff5-70c5-45cd-9c35-b36c25d77363', 'Darshan', '2020-01-21T05:47:08.644',
        '51381618-1bc9-4c19-aab9-44994433b183');

INSERT INTO public.stock_detail(id, managing_director, year_founded, asset_detail_id)
VALUES ('2ffedff5-70c5-45cd-9c35-b36c25d77364', 'Harsh', '2020-01-21T05:47:08.644',
        '51381618-1bc9-4c19-aab9-44994433b184');


/** Stock Statistic Data **/

INSERT INTO public.stock_statistic(book_value, div_yield, earning_per_sharettm, industrype, market_cap,
                                   number_of_stack_holders, pb_ratio, pe_ratio, return_on_equity, stock_detail_id)
VALUES (1.1, 2.2, 3.3, 4.4, 1000.1, 5, 0.5, 1.5, 50.4, '2ffedff5-70c5-45cd-9c35-b36c25d77361');


INSERT INTO public.stock_statistic(book_value, div_yield, earning_per_sharettm, industrype, market_cap,
                                   number_of_stack_holders, pb_ratio, pe_ratio, return_on_equity, stock_detail_id)
VALUES (1.1, 2.2, 3.3, 4.4, 1000.1, 5, 0.5, 1.5, 50.4, '2ffedff5-70c5-45cd-9c35-b36c25d77362');


INSERT INTO public.stock_statistic(book_value, div_yield, earning_per_sharettm, industrype, market_cap,
                                   number_of_stack_holders, pb_ratio, pe_ratio, return_on_equity, stock_detail_id)
VALUES (1.1, 2.2, 3.3, 4.4, 1000.1, 5, 0.5, 1.5, 50.4, '2ffedff5-70c5-45cd-9c35-b36c25d77363');

INSERT INTO public.stock_statistic(book_value, div_yield, earning_per_sharettm, industrype, market_cap,
                                   number_of_stack_holders, pb_ratio, pe_ratio, return_on_equity, stock_detail_id)
VALUES (1.1, 2.2, 3.3, 4.4, 1000.1, 5, 0.5, 1.5, 50.4, '2ffedff5-70c5-45cd-9c35-b36c25d77364');


/** Mutual fund details **/

INSERT INTO public.mutual_fund_detail(id, fund_manager, launch_date, asset_detail_id)
VALUES ('51381618-1bc9-4c19-aab9-000000000001', 'Harsh', '2020-01-21T05:47:08.644',
        '51381618-1bc9-4c19-aab9-44994433b185');

INSERT INTO public.mutual_fund_detail(id, fund_manager, launch_date, asset_detail_id)
VALUES ('51381618-1bc9-4c19-aab9-000000000002', 'Mohit', '2020-01-21T05:47:08.644',
        '51381618-1bc9-4c19-aab9-44994433b186');

INSERT INTO public.mutual_fund_detail(id, fund_manager, launch_date, asset_detail_id)
VALUES ('51381618-1bc9-4c19-aab9-000000000003', 'Nemish', '2020-01-21T05:47:08.644',
        '51381618-1bc9-4c19-aab9-44994433b187');

INSERT INTO public.mutual_fund_detail(id, fund_manager, launch_date, asset_detail_id)
VALUES ('51381618-1bc9-4c19-aab9-000000000004', 'Mohit', '2020-01-21T05:47:08.644',
        '51381618-1bc9-4c19-aab9-44994433b188');


/** Mutual Fund Statistics **/

INSERT INTO public.mutual_fund_statistic(expense_ratio, fund_size, fund_started, minsip, nav, risk, sip_allowed,
                                         mutual_fund_detail_id)
VALUES (0.0, 10, '2020-01-21T05:47:08.644', 100, 50, 'Low', true, '51381618-1bc9-4c19-aab9-000000000001');

INSERT INTO public.mutual_fund_statistic(expense_ratio, fund_size, fund_started, minsip, nav, risk, sip_allowed,
                                         mutual_fund_detail_id)
VALUES (0.0, 10, '2020-01-21T05:47:08.644', 100, 50, 'High', true, '51381618-1bc9-4c19-aab9-000000000002');

INSERT INTO public.mutual_fund_statistic(expense_ratio, fund_size, fund_started, minsip, nav, risk, sip_allowed,
                                         mutual_fund_detail_id)
VALUES (0.0, 10, '2020-01-21T05:47:08.644', 100, 50, 'High', true, '51381618-1bc9-4c19-aab9-000000000003');

INSERT INTO public.mutual_fund_statistic(expense_ratio, fund_size, fund_started, minsip, nav, risk, sip_allowed,
                                         mutual_fund_detail_id)
VALUES (0.0, 10, '2020-01-21T05:47:08.644', 100, 50, 'Low', true, '51381618-1bc9-4c19-aab9-000000000004');

/** User Data **/

INSERT INTO public.user_detail(id, date_of_birth, dpurl, email, gender, mobile_no, pan_card, signature_url, name)
VALUES ('00000000-0000-0000-0000-000000000000', '23/10/1999', 'dpurl', 'dummy1@gmail.com', 'MALE', '9876543211',
        'pancardnumber', 'signatureurl', 'XYZ');

INSERT INTO public.stock_price(id, price, stock_exchange, timestamp, stock_detail_id)
VALUES ('00000000-0000-0000-1000-000000000000', 100, 'BSE', '2020-01-21T05:47:08.644',
        '2ffedff5-70c5-45cd-9c35-b36c25d77361');

INSERT INTO public.mutual_fund_price(id, price, timestamp, mutual_fund_detail_id)
VALUES ('00000000-0000-0000-1100-000000000000', 100, 'Wed Mar 27 08:22:02 IST 2015',
        '51381618-1bc9-4c19-aab9-000000000001');