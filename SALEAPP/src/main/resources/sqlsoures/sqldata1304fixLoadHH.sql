-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: saleappktpm
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
  PRIMARY KEY (`idDonHang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donhang`
--

LOCK TABLES `donhang` WRITE;
/*!40000 ALTER TABLE `donhang` DISABLE KEYS */;
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
  `idLoaiHH` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idDonHang`,`idHangHoa`,`idLoaiHH`),
  KEY `fk_donhang_has_hanghoa_hanghoa1_idx` (`idHangHoa`,`idLoaiHH`),
  KEY `fk_donhang_has_hanghoa_donhang1_idx` (`idDonHang`),
  CONSTRAINT `fk_donhang_has_hanghoa_donhang1` FOREIGN KEY (`idDonHang`) REFERENCES `donhang` (`idDonHang`),
  CONSTRAINT `fk_donhang_has_hanghoa_hanghoa1` FOREIGN KEY (`idHangHoa`, `idLoaiHH`) REFERENCES `hanghoa` (`idHangHoa`, `idLoaiHH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donhang_hanghoa`
--

LOCK TABLES `donhang_hanghoa` WRITE;
/*!40000 ALTER TABLE `donhang_hanghoa` DISABLE KEYS */;
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
  `AnhHH` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'no',
  `SoLuong` double DEFAULT NULL,
  `KG` double DEFAULT NULL,
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
INSERT INTO `hanghoa` VALUES ('1','Pepsi',10000,'Vi???t Nam','1','pepsi',40,0),('2','Spire',10000,'Vi???t Nam','1','pepsi',2,0),('3','S???a',240000,'Vi???t Nam','3','locsua',3,0),('4','Snack',10000,'Vi???t Nam','2','snacklay',5,0),('5','Snacklay',120000,'Vi???t Nam','2','ssss',666,0),('6','SSSS',123,'Vi???t Nam','1','pepsi',56,0),('7','qqqq',123,'Vi???t Nam','1','pepsi',333,NULL);
/*!40000 ALTER TABLE `hanghoa` ENABLE KEYS */;
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
  PRIMARY KEY (`idLoaiHH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaihanghoa`
--

LOCK TABLES `loaihanghoa` WRITE;
/*!40000 ALTER TABLE `loaihanghoa` DISABLE KEYS */;
INSERT INTO `loaihanghoa` VALUES ('1','N?????c ng???t','Chai'),('2','B??nh','G??i'),('3','S???a','L???c');
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
  `GioiTinh` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Active` tinyint NOT NULL DEFAULT '0',
  `Email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `NgayTao` date NOT NULL,
  `SDT` int NOT NULL,
  `Role` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idNguoiDung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoidung`
--

LOCK TABLES `nguoidung` WRITE;
/*!40000 ALTER TABLE `nguoidung` DISABLE KEYS */;
INSERT INTO `nguoidung` VALUES ('1','Lam','Lam','123','Nam',1,'lam@gmail.com','2001-02-02',123456789,'NhanVien'),('2','Huy','Huy','456','Nu',1,'huy@gmail.com','2002-03-03',987654321,'QuanLy'),('3','Trong','Trong','0336','Nam',1,'trong@gmail.com','2005-07-07',555555555,'QuanLy');
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

-- Dump completed on 2022-04-13 20:38:13
