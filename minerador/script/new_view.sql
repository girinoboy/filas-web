CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `new_view` AS
    select 
        `vda`.`Data` AS `Data`,
        sum((`pro`.`Valor_unitario` * `ret`.`Quantidade`)) AS `Venda`,
        date_format(`vda`.`Data`, '%d') AS `dia`,
        date_format(`vda`.`Data`, '%m') AS `mes`,
        date_format(`vda`.`Data`, '%Y') AS `ano`,
        NULL AS `custo`,
        NULL AS `quantidade`
    from
        ((`venda` `vda`
        join `retirada` `ret` ON ((`vda`.`Codigo_NF` = `ret`.`Codigo_NF`)))
        join `produto` `pro` ON ((`ret`.`Codigo_Produto` = `pro`.`Codigo_Produto`)))
where date_format(`vda`.`Data`, '%m') = 5
    group by 1