-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: notebook
-- ------------------------------------------------------
-- Server version	5.7.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asset`
--

DROP TABLE IF EXISTS `asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset` (
  `id` int(11) NOT NULL COMMENT '自增主键',
  `company_code` varchar(16) DEFAULT NULL COMMENT '公司代码',
  `asset_number_m` varchar(16) DEFAULT NULL COMMENT '主资产号',
  `asset_number_s` varchar(4) DEFAULT NULL COMMENT '次资产号',
  `description` varchar(64) DEFAULT NULL COMMENT '资产描述',
  `io_location` varchar(64) DEFAULT NULL COMMENT '出入位置',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(8) DEFAULT NULL COMMENT '创建者',
  `updator` varchar(45) DEFAULT NULL COMMENT '更新者',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除 1:已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset`
--

LOCK TABLES `asset` WRITE;
/*!40000 ALTER TABLE `asset` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_allot`
--

DROP TABLE IF EXISTS `asset_allot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_allot` (
  `id` int(11) NOT NULL COMMENT '自增主键',
  `company_code` varchar(16) DEFAULT NULL COMMENT '公司代码',
  `asset_number_m` varchar(16) DEFAULT NULL COMMENT '主资产号',
  `asset_number_s` varchar(4) DEFAULT NULL COMMENT '次资产号',
  `io_location` varchar(64) DEFAULT NULL COMMENT '出入位置',
  `valid_date_end` datetime DEFAULT NULL COMMENT '有效日期结束',
  `admin` varchar(32) DEFAULT NULL COMMENT '负责人',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(8) DEFAULT NULL COMMENT '创建者',
  `updator` varchar(45) DEFAULT NULL COMMENT '更新者',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除 1:已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='时间相关资产分配';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_allot`
--

LOCK TABLES `asset_allot` WRITE;
/*!40000 ALTER TABLE `asset_allot` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset_allot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_io_mail_record`
--

DROP TABLE IF EXISTS `asset_io_mail_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_io_mail_record` (
  `id` int(11) NOT NULL COMMENT '自增主键',
  `company_code` varchar(16) DEFAULT NULL COMMENT '公司代码',
  `asset_number_m` varchar(16) DEFAULT NULL COMMENT '主资产号',
  `asset_number_s` int(1) DEFAULT NULL COMMENT '次资产号',
  `asset_des` varchar(8) DEFAULT NULL COMMENT '资产描述',
  `admin` varchar(8) DEFAULT NULL COMMENT '负责人',
  `admin_name` varchar(8) DEFAULT NULL COMMENT '负责人姓名',
  `emp_code` varchar(16) DEFAULT NULL COMMENT '员工编码',
  `emp_name` varchar(16) DEFAULT NULL COMMENT '员工姓名',
  `factory_code` varchar(16) DEFAULT NULL COMMENT '厂区门编码',
  `factory_des` varchar(16) DEFAULT NULL COMMENT '厂区门描述',
  `position_code` varchar(16) DEFAULT NULL COMMENT '位置',
  `position_des` varchar(16) DEFAULT NULL COMMENT '位置描述',
  `innser_flag` int(1) DEFAULT NULL COMMENT '是否厂内门',
  `io_date` varchar(16) DEFAULT NULL COMMENT '当前时间和出入时间差(MIN)',
  `io_time` varchar(16) DEFAULT NULL COMMENT '出入时差(MIN)',
  `allow_flag` int(1) DEFAULT NULL COMMENT '准入标记',
  `in_flag` int(1) DEFAULT NULL COMMENT '是否放行',
  `time_text` varchar(32) DEFAULT NULL COMMENT '出入时间文本',
  `superior_code` varchar(16) DEFAULT NULL COMMENT '汇报上司编号',
  `superior_name` varchar(16) DEFAULT NULL COMMENT '上司名称',
  `superior_mail` varchar(16) DEFAULT NULL COMMENT '上司邮箱',
  `creator` varchar(8) DEFAULT NULL COMMENT '创建者',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updator` varchar(45) DEFAULT NULL COMMENT '更新者',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除 1:已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_io_mail_record`
--

LOCK TABLES `asset_io_mail_record` WRITE;
/*!40000 ALTER TABLE `asset_io_mail_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset_io_mail_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_io_record`
--

DROP TABLE IF EXISTS `asset_io_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_io_record` (
  `id` int(11) NOT NULL COMMENT '流水号,自增主键',
  `company_code` varchar(16) DEFAULT NULL COMMENT '公司代码',
  `asset_number_m` varchar(16) DEFAULT NULL COMMENT '主资产号',
  `asset_number_s` varchar(4) DEFAULT NULL COMMENT '次资产号',
  `admin` varchar(8) DEFAULT NULL COMMENT '负责人',
  `factory_gate_code` varchar(16) DEFAULT NULL COMMENT '厂区门编码',
  `io_type` int(1) DEFAULT NULL COMMENT '出入标记（0：入，1：出）',
  `source_position` varchar(16) DEFAULT NULL COMMENT '原位置',
  `destination_position` varchar(16) DEFAULT NULL COMMENT '目的位置',
  `io_date` date DEFAULT NULL COMMENT '出入日期',
  `io_time` time DEFAULT NULL COMMENT '出入时间',
  `allow_flag` int(1) DEFAULT NULL COMMENT '准入标记',
  `text` varchar(32) DEFAULT NULL COMMENT '文本字段',
  `operate` varchar(8) DEFAULT NULL COMMENT '操作账号',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `valid_date_start` date DEFAULT NULL COMMENT '有效起始日期',
  `valid_date_end` date DEFAULT NULL COMMENT '有效结束日期',
  `in_flag` int(1) DEFAULT NULL COMMENT '是否放行',
  `creator` varchar(8) DEFAULT NULL COMMENT '创建者',
  `createtime_copy1` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime_copy1` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updator` varchar(45) DEFAULT NULL COMMENT '更新者',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除 1:已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产出入记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_io_record`
--

LOCK TABLES `asset_io_record` WRITE;
/*!40000 ALTER TABLE `asset_io_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset_io_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asset_time`
--

DROP TABLE IF EXISTS `asset_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset_time` (
  `id` int(11) NOT NULL COMMENT '流水号,自增主键',
  `company_code` varchar(16) DEFAULT NULL COMMENT '公司代码',
  `asset_number_m` varchar(16) DEFAULT NULL COMMENT '主资产号',
  `asset_number_s` varchar(4) DEFAULT NULL COMMENT '次资产号',
  `admin` varchar(8) DEFAULT NULL COMMENT '负责人',
  `factory_gate_code` varchar(16) DEFAULT NULL COMMENT '厂区门编码',
  `io_type` int(1) DEFAULT NULL COMMENT '出入标记（0：入，1：出）',
  `source_position` varchar(16) DEFAULT NULL COMMENT '原位置',
  `destination_position` varchar(16) DEFAULT NULL COMMENT '目的位置',
  `io_date` date DEFAULT NULL COMMENT '出入日期',
  `io_time` int(11) DEFAULT NULL COMMENT '出入时长(min)',
  `allow_flag` int(1) DEFAULT NULL COMMENT '准入标记',
  `text` varchar(32) DEFAULT NULL COMMENT '文本字段',
  `operate` varchar(8) DEFAULT NULL COMMENT '操作账号',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `valid_date_start` date DEFAULT NULL COMMENT '有效起始日期',
  `valid_date_end` date DEFAULT NULL COMMENT '有效结束日期',
  `in_flag` int(1) DEFAULT NULL COMMENT '是否放行',
  `creator` varchar(8) DEFAULT NULL COMMENT '创建者',
  `createtime_copy1` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime_copy1` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updator` varchar(45) DEFAULT NULL COMMENT '更新者',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除 1:已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产出入记录时长';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset_time`
--

LOCK TABLES `asset_time` WRITE;
/*!40000 ALTER TABLE `asset_time` DISABLE KEYS */;
/*!40000 ALTER TABLE `asset_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `common_configure`
--

DROP TABLE IF EXISTS `common_configure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `common_configure` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `code` int(11) DEFAULT NULL COMMENT '编码',
  `description` varchar(64) DEFAULT NULL COMMENT '描述',
  `value` varchar(64) DEFAULT NULL COMMENT '具体值',
  `creator` varchar(8) DEFAULT NULL COMMENT '创建者',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updator` varchar(45) DEFAULT NULL COMMENT '更新者',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除 1:已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `common_configure`
--

LOCK TABLES `common_configure` WRITE;
/*!40000 ALTER TABLE `common_configure` DISABLE KEYS */;
/*!40000 ALTER TABLE `common_configure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `mandt` int(11) NOT NULL,
  `ZPS01` varchar(8) DEFAULT NULL COMMENT '员工编号',
  `ZPS02` varchar(10) DEFAULT NULL COMMENT '员工姓名',
  `ZPS03` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `ZPS04` varchar(1) DEFAULT NULL COMMENT '状态',
  `creator` varchar(8) DEFAULT NULL COMMENT '创建者',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updator` varchar(45) DEFAULT NULL COMMENT '更新者',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除 1:已删除)',
  PRIMARY KEY (`mandt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_rel`
--

DROP TABLE IF EXISTS `employee_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_rel` (
  `mandt` int(11) NOT NULL COMMENT '集团',
  `zpernr` varchar(8) DEFAULT NULL COMMENT '工号',
  `ztelephone` varchar(25) DEFAULT NULL COMMENT '联系方式',
  `zdepartment` varchar(20) DEFAULT NULL COMMENT '部门',
  `zleader` varchar(8) DEFAULT NULL COMMENT '汇报上司',
  `zstate` varchar(1) DEFAULT NULL COMMENT '是否停用',
  `creator` varchar(8) DEFAULT NULL COMMENT '创建者',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updator` varchar(45) DEFAULT NULL COMMENT '更新者',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除 1:已删除)',
  PRIMARY KEY (`mandt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员联系方式';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_rel`
--

LOCK TABLES `employee_rel` WRITE;
/*!40000 ALTER TABLE `employee_rel` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factory_gate`
--

DROP TABLE IF EXISTS `factory_gate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factory_gate` (
  `id` int(11) NOT NULL COMMENT '自增主键',
  `code` varchar(16) DEFAULT NULL COMMENT '厂区门编码',
  `description` varchar(32) DEFAULT NULL COMMENT '厂区门描述',
  `admin` varchar(8) DEFAULT NULL COMMENT '负责人',
  `inner_flag` int(1) DEFAULT NULL COMMENT '是否厂内门(1:内部门，2：场外门入厂，3：厂外门出厂)',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `updator` varchar(32) DEFAULT NULL COMMENT '更新人',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产区门维护表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factory_gate`
--

LOCK TABLES `factory_gate` WRITE;
/*!40000 ALTER TABLE `factory_gate` DISABLE KEYS */;
/*!40000 ALTER TABLE `factory_gate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factory_position`
--

DROP TABLE IF EXISTS `factory_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factory_position` (
  `id` int(11) NOT NULL COMMENT '自增主键',
  `factory_code` varchar(16) DEFAULT NULL COMMENT '厂区门编码',
  `position_code` varchar(16) DEFAULT NULL COMMENT '对应位置编码',
  `type` int(1) DEFAULT NULL COMMENT '出入标记',
  `creator` varchar(8) DEFAULT NULL COMMENT '创建者',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updator` varchar(45) DEFAULT NULL COMMENT '更新者',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除 1:已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门出入对应位置信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factory_position`
--

LOCK TABLES `factory_position` WRITE;
/*!40000 ALTER TABLE `factory_position` DISABLE KEYS */;
/*!40000 ALTER TABLE `factory_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notebook_asset_info`
--

DROP TABLE IF EXISTS `notebook_asset_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notebook_asset_info` (
  `id` int(11) NOT NULL COMMENT '自增ID',
  `asset_no` varchar(20) NOT NULL COMMENT '资产编号',
  `sub_asset_no` varchar(4) NOT NULL COMMENT '次级资产编号',
  `effective_start` date DEFAULT NULL COMMENT '有效起始时间',
  `effective_end` date DEFAULT NULL COMMENT '有效结束时间',
  `creator` varchar(8) DEFAULT NULL COMMENT '创建者',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updator` varchar(45) DEFAULT NULL COMMENT '更新者',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除 1:已删除)',
  PRIMARY KEY (`id`,`asset_no`,`sub_asset_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='笔记本准入信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notebook_asset_info`
--

LOCK TABLES `notebook_asset_info` WRITE;
/*!40000 ALTER TABLE `notebook_asset_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `notebook_asset_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notebook_asset_info_oa`
--

DROP TABLE IF EXISTS `notebook_asset_info_oa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notebook_asset_info_oa` (
  `id` int(11) NOT NULL COMMENT '自增ID',
  `sequence_no` varchar(20) NOT NULL COMMENT '申请单号',
  `asset_no` varchar(20) NOT NULL COMMENT '资产编号',
  `sub_asset_no` varchar(4) NOT NULL COMMENT '次级资产编号',
  `effective_start` date DEFAULT NULL COMMENT '有效起始时间',
  `effective_end` date DEFAULT NULL COMMENT '有效结束时间',
  `creator` varchar(8) DEFAULT NULL COMMENT '创建者',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updator` varchar(45) DEFAULT NULL COMMENT '更新者',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除 1:已删除)',
  PRIMARY KEY (`id`,`sequence_no`,`asset_no`,`sub_asset_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='笔记本准入信息（OA传输）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notebook_asset_info_oa`
--

LOCK TABLES `notebook_asset_info_oa` WRITE;
/*!40000 ALTER TABLE `notebook_asset_info_oa` DISABLE KEYS */;
/*!40000 ALTER TABLE `notebook_asset_info_oa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_info`
--

DROP TABLE IF EXISTS `position_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_info` (
  `id` int(11) NOT NULL COMMENT '自增主键',
  `code` varchar(16) DEFAULT NULL COMMENT '位置编码',
  `description` varchar(32) DEFAULT NULL COMMENT '位置描述',
  `innser_flag` int(1) NOT NULL DEFAULT '0' COMMENT '是否厂内门（0:不是,1:是）',
  `creator` varchar(8) DEFAULT NULL COMMENT '创建者',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updator` varchar(45) DEFAULT NULL COMMENT '更新者',
  `del_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未删除 1:已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_info`
--

LOCK TABLES `position_info` WRITE;
/*!40000 ALTER TABLE `position_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `position_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'notebook'
--

--
-- Dumping routines for database 'notebook'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-26  0:17:37
