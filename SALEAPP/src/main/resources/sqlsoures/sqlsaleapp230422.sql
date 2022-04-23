-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: saleappktpm2
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chinhanh`
--

DROP TABLE IF EXISTS `chinhanh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chinhanh` (
  `idChiNhanh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TenChiNhanh` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `DiaChi` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idNguoiDung` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Active` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`idChiNhanh`,`idNguoiDung`),
  KEY `fk_chinhanh_nguoidung_idx` (`idNguoiDung`),
  CONSTRAINT `fk_chinhanh_nguoidung` FOREIGN KEY (`idNguoiDung`) REFERENCES `nguoidung` (`idNguoiDung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chinhanh`
--

LOCK TABLES `chinhanh` WRITE;
/*!40000 ALTER TABLE `chinhanh` DISABLE KEYS */;
INSERT INTO `chinhanh` VALUES ('1','A','A','1',1);
/*!40000 ALTER TABLE `chinhanh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chinhanh_hanghoa`
--

DROP TABLE IF EXISTS `chinhanh_hanghoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chinhanh_hanghoa` (
  `idChiNhanh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idNguoiDung` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idHangHoa` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idLoaiHH` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idChiNhanh`,`idNguoiDung`,`idHangHoa`,`idLoaiHH`),
  KEY `fk_chinhanh_has_hanghoa_hanghoa1_idx` (`idHangHoa`,`idLoaiHH`),
  KEY `fk_chinhanh_has_hanghoa_chinhanh1_idx` (`idChiNhanh`,`idNguoiDung`),
  CONSTRAINT `fk_chinhanh_has_hanghoa_chinhanh1` FOREIGN KEY (`idChiNhanh`, `idNguoiDung`) REFERENCES `chinhanh` (`idChiNhanh`, `idNguoiDung`),
  CONSTRAINT `fk_chinhanh_has_hanghoa_hanghoa1` FOREIGN KEY (`idHangHoa`, `idLoaiHH`) REFERENCES `hanghoa` (`idHangHoa`, `idLoaiHH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chinhanh_hanghoa`
--

LOCK TABLES `chinhanh_hanghoa` WRITE;
/*!40000 ALTER TABLE `chinhanh_hanghoa` DISABLE KEYS */;
/*!40000 ALTER TABLE `chinhanh_hanghoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donhang`
--

DROP TABLE IF EXISTS `donhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donhang` (
  `idDonHang` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NgayTao` date NOT NULL,
  `TenNV` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoLuong` double NOT NULL,
  `ThanhTien` double NOT NULL,
  `KhuyenMai` double DEFAULT NULL,
  `TienKH` double NOT NULL,
  `TienThoi` double NOT NULL,
  `idNguoiDung` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idKhachHang` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idDonHang`,`idNguoiDung`),
  KEY `fk_donhang_nguoidung1_idx` (`idNguoiDung`),
  CONSTRAINT `fk_donhang_nguoidung1` FOREIGN KEY (`idNguoiDung`) REFERENCES `nguoidung` (`idNguoiDung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donhang`
--

LOCK TABLES `donhang` WRITE;
/*!40000 ALTER TABLE `donhang` DISABLE KEYS */;
INSERT INTO `donhang` VALUES ('0dd764e0-e41a-4341-adee-08495a32e97c','2022-04-21','Lam',2,1216000,0.1,2000000,784000,'1','1'),('0f439136-d685-47d1-b15c-222c4f065f97','2022-04-23','Lam',1,12000,0,13000,1000,'1','null'),('5a3745b5-ef94-4375-b363-32eda3fd2a11','2022-04-21','Lam',2,1203000,0,2000000,797000,'1','null'),('5e1add61-e2a8-40e4-969c-06e403c26e36','2022-04-22','Lam',1,11000,0,22000,11000,'1','null'),('b9995c18-d8d4-4aa1-85aa-42433f45f1a2','2022-04-21','Lam',2,21000,0,30000,9000,'1','null'),('d8987bec-c493-4ff8-b040-911b8486755e','2022-04-23','Sang',2,11000,0.5,200000,189000,'4','2'),('db34f738-27a6-4dbf-87cb-40700cc9531b','2022-04-23','Lam',3,2432000,0.1,20000000,17568000,'1','null');
/*!40000 ALTER TABLE `donhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donhang_hanghoa`
--

DROP TABLE IF EXISTS `donhang_hanghoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donhang_hanghoa` (
  `idDonHang` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idHangHoa` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idDonHang`,`idHangHoa`),
  KEY `fk_donhang_has_hanghoa_donhang1_idx` (`idDonHang`),
  KEY `fk_donhang_has_hanghoa_hanghoa1_idx` (`idHangHoa`),
  CONSTRAINT `fk_donhang_has_hanghoa_donhang1` FOREIGN KEY (`idDonHang`) REFERENCES `donhang` (`idDonHang`),
  CONSTRAINT `fk_donhang_has_hanghoa_hanghoa1` FOREIGN KEY (`idHangHoa`) REFERENCES `hanghoa` (`idHangHoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donhang_hanghoa`
--

LOCK TABLES `donhang_hanghoa` WRITE;
/*!40000 ALTER TABLE `donhang_hanghoa` DISABLE KEYS */;
INSERT INTO `donhang_hanghoa` VALUES ('0dd764e0-e41a-4341-adee-08495a32e97c','4'),('0f439136-d685-47d1-b15c-222c4f065f97','3'),('5a3745b5-ef94-4375-b363-32eda3fd2a11','1'),('5a3745b5-ef94-4375-b363-32eda3fd2a11','2'),('5e1add61-e2a8-40e4-969c-06e403c26e36','3529187b-cdb4-4d21-98d3-1f158c8fe944'),('b9995c18-d8d4-4aa1-85aa-42433f45f1a2','2'),('b9995c18-d8d4-4aa1-85aa-42433f45f1a2','3'),('d8987bec-c493-4ff8-b040-911b8486755e','2'),('d8987bec-c493-4ff8-b040-911b8486755e','3'),('db34f738-27a6-4dbf-87cb-40700cc9531b','1'),('db34f738-27a6-4dbf-87cb-40700cc9531b','2'),('db34f738-27a6-4dbf-87cb-40700cc9531b','3');
/*!40000 ALTER TABLE `donhang_hanghoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hanghoa`
--

DROP TABLE IF EXISTS `hanghoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hanghoa` (
  `idHangHoa` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TenHangHoa` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Gia` double NOT NULL,
  `XuatXu` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `idLoaiHH` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AnhHH` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'no',
  `SoLuong` double DEFAULT NULL,
  `KG` double DEFAULT NULL,
  `Active` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`idHangHoa`,`idLoaiHH`),
  KEY `fk_hanghoa_loaihanghoa1_idx` (`idLoaiHH`),
  CONSTRAINT `fk_hanghoa_loaihanghoa1` FOREIGN KEY (`idLoaiHH`) REFERENCES `loaihanghoa` (`idLoaiHH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hanghoa`
--

LOCK TABLES `hanghoa` WRITE;
/*!40000 ALTER TABLE `hanghoa` DISABLE KEYS */;
INSERT INTO `hanghoa` VALUES ('1','Pepsi',1200000,'Việt Nam','1','pepsi',21,0,1),('2','Spire',10000,'Việt Nam','1','sprite',7,0,1),('3','Sửa',12000,'Việt Nam','3','locsua',18,0,1),('3529187b-cdb4-4d21-98d3-1f158c8fe944','SnackLay',11000,'Việt Nam','2','snacklay',12,0,1),('4','Snack Hành',22000,'Việt Nam','2','snackhanh',0,23,1);
/*!40000 ALTER TABLE `hanghoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hanghoa_quidinh`
--

DROP TABLE IF EXISTS `hanghoa_quidinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hanghoa_quidinh` (
  `idHangHoa` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idQuiDinh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idHangHoa`,`idQuiDinh`),
  KEY `fk_hanghoa_has_quidinh_quidinh1_idx` (`idQuiDinh`),
  KEY `fk_hanghoa_has_quidinh_hanghoa1_idx` (`idHangHoa`),
  CONSTRAINT `fk_hanghoa_has_quidinh_hanghoa1` FOREIGN KEY (`idHangHoa`) REFERENCES `hanghoa` (`idHangHoa`),
  CONSTRAINT `fk_hanghoa_has_quidinh_quidinh1` FOREIGN KEY (`idQuiDinh`) REFERENCES `quidinh` (`idQuiDinh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hanghoa_quidinh`
--

LOCK TABLES `hanghoa_quidinh` WRITE;
/*!40000 ALTER TABLE `hanghoa_quidinh` DISABLE KEYS */;
INSERT INTO `hanghoa_quidinh` VALUES ('1','1'),('2','2');
/*!40000 ALTER TABLE `hanghoa_quidinh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khuyenmai`
--

DROP TABLE IF EXISTS `khuyenmai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khuyenmai` (
  `idKhuyenMai` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ChucVu` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Giam` double DEFAULT NULL,
  `Active` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`idKhuyenMai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khuyenmai`
--

LOCK TABLES `khuyenmai` WRITE;
/*!40000 ALTER TABLE `khuyenmai` DISABLE KEYS */;
INSERT INTO `khuyenmai` VALUES ('1','QuanLy',0.5,1),('2','NhanVien',0.2,1);
/*!40000 ALTER TABLE `khuyenmai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaihanghoa`
--

DROP TABLE IF EXISTS `loaihanghoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaihanghoa` (
  `idLoaiHH` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TenLoaiHH` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `DonVi` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Active` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`idLoaiHH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaihanghoa`
--

LOCK TABLES `loaihanghoa` WRITE;
/*!40000 ALTER TABLE `loaihanghoa` DISABLE KEYS */;
INSERT INTO `loaihanghoa` VALUES ('1','Nước ngọt','Chai',1),('2','Bánh','Gói',1),('3','Sữa','Lốc',1);
/*!40000 ALTER TABLE `loaihanghoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoidung`
--

DROP TABLE IF EXISTS `nguoidung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nguoidung` (
  `idNguoiDung` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TenNguoiDung` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `TaiKhoan` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MatKhau` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `GioiTinh` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Active` tinyint NOT NULL DEFAULT '0',
  `Email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `NgayTao` date NOT NULL,
  `SDT` int NOT NULL,
  `Role` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idNguoiDung`),
  UNIQUE KEY `SDT_UNIQUE` (`SDT`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoidung`
--

LOCK TABLES `nguoidung` WRITE;
/*!40000 ALTER TABLE `nguoidung` DISABLE KEYS */;
INSERT INTO `nguoidung` VALUES ('1','Lam','Lam','123','2001-02-02','Nam',1,'lam@gmail.com','2001-02-02',123456789,'NhanVien'),('2','Huy','Huy','456','2001-02-02','Nu',1,'huy@gmail.com','2002-03-03',987654321,'QuanLy'),('3','Trong','Trong','0336','2001-04-20','Nam',1,'trong@gmail.com','2005-07-07',123,'KhachHang'),('4','Sang','Sang','0336','2001-04-20','Nam',1,'Sang@gmail.com','2005-07-07',789,'NhanVien');
/*!40000 ALTER TABLE `nguoidung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quidinh`
--

DROP TABLE IF EXISTS `quidinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quidinh` (
  `idQuiDinh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `GiamGia` double DEFAULT NULL,
  `Tren1TrieuSN` double DEFAULT NULL,
  `NgayBD` date NOT NULL,
  `NgayKT` date NOT NULL,
  `idNguoiDung` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Active` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`idQuiDinh`,`idNguoiDung`),
  KEY `fk_quidinh_nguoidung1_idx` (`idNguoiDung`),
  CONSTRAINT `fk_quidinh_nguoidung1` FOREIGN KEY (`idNguoiDung`) REFERENCES `nguoidung` (`idNguoiDung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quidinh`
--

LOCK TABLES `quidinh` WRITE;
/*!40000 ALTER TABLE `quidinh` DISABLE KEYS */;
INSERT INTO `quidinh` VALUES ('1',6000,0,'2001-02-02','2023-02-02','1',0),('2',1000,0,'2001-02-02','2023-02-02','1',1);
/*!40000 ALTER TABLE `quidinh` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-23 17:42:14
