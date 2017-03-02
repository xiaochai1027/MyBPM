
USE i360r_deployer;

-- 自己体验号待办事项app配置
INSERT INTO `t_project_property` (`key`,`value`,`project_id`) VALUES ('WEIXIN_QYH_CORPID', 'wx5b0158778c6ad499', 9), 
('WEIXIN_QYH_MANAGE_GROUP_SECRET', 'yrbdvdGMk5x6tCZKnI8iFwA39AZxyIpw0WP-4G-hGqwmGpCvsCTCHUN4bjKJcZI7', 9),
('WEIXIN_QYH_APP_ID', '21', 9),
('WEIXIN_QYH_APP_TOKEN', 'KDDzO0vRp6H1BVQ9UfTtxdwAz0', 9),
('WEIXIN_QYH_APP_ENCODINGAESKEY', 'Epg8baLbAXHGITJL6EliYoco8M6hM3BtzP7gAYN21ko', 9);

-- 把通讯录中的微信企业号设置修改成自己体验号
update `t_project_property` set `value`='wx5b0158778c6ad499' where `key`='PLATFORM_WEIXIN_QYH_CORP_ID' and project_id = 1;
update `t_project_property` set `value`='l1dTa7M1dDBadoXyDv-fmWP6dVjDeIDP7gTPHGaiaJm2qfMDZbB3FqVvyZHftFzK' where `key`='PLATFORM_WEIXIN_QYH_CONTACT_PERMISSION_MANAGE_GROUP_SECRET' and project_id = 1;

INSERT INTO `t_project_property` (`key`,`value`,`project_id`) VALUES 
('HTTP_CLIENT_MAX_CONNECTION', '150', 9),
('HTTP_CLIENT_CONNECTION_TIMEOUT', '10000', 9),
('HTTP_CLIENT_SOCKET_TIMEOUT', '10000', 9),
('SERVER_URL', 'http://bpm.jiahuanle.com', 9),
('ACTIVEMQ_DATA_ROOT', '/home/shbj/test02/activemq/bpm', 9);


-- 修改其他原有命名不标准的配置项
UPDATE `t_application_property` SET `key`='PLATFORM_SERVICE_SERVER_URL' WHERE `key`='PLATFORM_SERVER_URL' AND `application_id` = 23;
