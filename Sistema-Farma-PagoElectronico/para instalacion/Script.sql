
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------
DELIMITER $$

DROP PROCEDURE IF EXISTS `pagoProveedor` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `pagoProveedor`(in id int,in fecha date,in montoPago decimal(9,2), montoReal decimal(9,2), esSoles boolean, in tipoPago varchar(45),
in idBancoEmisor int, idBancoProveedor int ,in idCuenta int,in numeroCheque varchar(20),in idFactura int, in evt int)
BEGIN

IF evt = 0 then
  IF (tipoPago='Efectivo') THEN
    INSERT INTO pagoproveedor VALUES(null,fecha,montoPago,montoReal,0,null,null,null,null,idFactura,esSoles);
  ELSE
    IF (tipoPago='Cheque') then
      INSERT INTO pagoproveedor values(null,fecha,montoPago,montoReal,1,idBancoEmisor,null,null,numeroCheque,idFactura, esSoles);
    ELSE
      INSERT INTO pagoproveedor values(null,fecha,montoPago,montoReal,2,idBancoEmisor,idBancoProveedor,idCuenta,null,idFactura,esSoles);
    END IF;
  END IF;
END IF;
IF evt = 1 then
  IF (tipoPago='Efectivo') THEN
    UPDATE pagoproveedor p SET  p.`tipoPago`=0, p.`fechapago`=fecha, p.`montoPago`=montoPago, p.`montoReal`=montoReal, p.`esSoles`=esSoles
    WHERE p.`idPagoProveedor`=id;
  ELSE
    IF (tipoPago='Cheque') then
      UPDATE pagoproveedor p SET p.`tipoPago`=1, p.`fechapago`=fecha, p.`montoPago`=montoPago, p.`montoReal`=montoReal, p.`esSoles`=esSoles, p.`idBancoEmisor`=idBancoEmisor, p.`numeroCheque`=numeroCheque
      WHERE p.`idPagoProveedor`=id;
    ELSE
      UPDATE pagoproveedor p SET p.`tipoPago`=2, p.`fechapago`=fecha, p.`montoPago`=montoPago, p.`montoReal`=montoReal, p.`esSoles`=esSoles, p.`idBancoEmisor`=idBancoEmisor, p.`idBancoProveedor`=idBancoProveedor, p.`idCuentaProveedor`=idCuenta
      WHERE p.`idPagoProveedor` = id;
    END IF;
  END IF;
END IF;
IF evt=2 THEN
  DELETE FROM pagoproveedor WHERE idPagoProveedor=id;
END IF;

END $$

DELIMITER ;
-- -------------------------------------------------------------------------------
ALTER TABLE `bdabarrotes`.`facturaproveedor` ADD COLUMN `totalSoles` DECIMAL(9,2) NOT NULL DEFAULT 0 AFTER `idTipoComprobante`,
 ADD COLUMN `enSoles` BOOLEAN NOT NULL DEFAULT 1 AFTER `totalSoles`;
-- -------------------------------------------------------------------------------
ALTER TABLE `bdabarrotes`.`pagoproveedor` CHANGE COLUMN `enSoles` `esSoles` TINYINT(1) NOT NULL DEFAULT 1;
-- ---------------------------------------------------------------------------------
ALTER TABLE `bdabarrotes`.`pagoproveedor` CHANGE COLUMN `monto` `montoPago` DECIMAL(9,2) NOT NULL DEFAULT '0.00',
 CHANGE COLUMN `montoSoles` `montoReal` DECIMAL(9,2) NOT NULL DEFAULT '0.00';
-- ------------------------------------------------------------------------------
DROP VIEW IF EXISTS `vta_catalogo`;
CREATE OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vta_catalogo` AS select `p`.`idProduto` AS `ID`,concat(`tp`.`nomtip`,' ',`m`.`nommarc`,' ',`p`.`nomproducto`) AS `Descripcion`,`u`.`nomuniddes` AS `Unidad`,`p`.`StockMinimo` AS `StockMinimo`,`p`.`precxmayo` AS `Precxmayor`,`p`.`precxmeno` AS `Precxmenor`,`p`.`precioCompra` AS `precioCompra`,`p`.`grabado` AS `grabado`,`m`.`nommarc` AS `marca`,`tp`.`nomtip` AS `tipo`,date_format(`p`.`fechaCaducidad`,'%d/%m/%Y') AS `fechaCaducidad`,`p`.`idUnidades`,u.pormayor from (((`produto` `p` join `marca` `m`) join `tipoproducto` `tp`) join `unidades` `u`) where ((`p`.`idMarca` = `m`.`idMarca`) and (`p`.`idUnidades` = `u`.`idUnidades`) and (`p`.`idTipoProducto` = `tp`.`idTipoProducto`));