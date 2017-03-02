
USE i360r_deployer;

-- 自己体验号待办事项管理组密钥，上线时需改成线上的
INSERT INTO `t_project_property` (`key`,`value`,`project_id`) VALUES ('WEIXIN_QYH_CORPID', 'wx5b0158778c6ad499', 9), 
('WEIXIN_QYH_MANAGE_GROUP_SECRET', 'yrbdvdGMk5x6tCZKnI8iFwA39AZxyIpw0WP-4G-hGqwmGpCvsCTCHUN4bjKJcZI7', 9),
('WEIXIN_QYH_APP_ID', '21', 9),
('WEIXIN_QYH_APP_TOKEN', 'KDDzO0vRp6H1BVQ9UfTtxdwAz0', 9),
('WEIXIN_QYH_APP_ENCODINGAESKEY', 'Epg8baLbAXHGITJL6EliYoco8M6hM3BtzP7gAYN21ko', 9);


INSERT INTO `t_project_property` (`key`,`value`,`project_id`) VALUES 
('HTTP_CLIENT_MAX_CONNECTION', '150', 9),
('HTTP_CLIENT_CONNECTION_TIMEOUT', '10000', 9),
('HTTP_CLIENT_SOCKET_TIMEOUT', '10000', 9),
('SERVER_URL', 'http://bpm.i360r.com', 9),
('ACTIVEMQ_DATA_ROOT', '/home/online/activemq/bpm', 9);


-- 修改其他原有命名不标准的配置项
UPDATE `t_application_property` SET `key`='PLATFORM_SERVICE_SERVER_URL' WHERE `key`='PLATFORM_SERVER_URL' AND `application_id` = 23;