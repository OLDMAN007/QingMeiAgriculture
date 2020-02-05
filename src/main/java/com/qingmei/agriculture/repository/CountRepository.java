package com.qingmei.agriculture.repository;

import com.qingmei.agriculture.entity.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountRepository extends CrudRepository<Sale, String> {

    /*
    1. 某顧客指定日期內的購物信息
	select customer.cus_name as cusName, commodity.com_name as comName, sale.quantity, sale.price, measurement.name as measurement, sale.date
	from sale left
	join customer on sale.customer_id = customer.id
	left join commodity on sale.commodity_id = commodity.id
	left join measurement on commodity.measurement = measurement.id
	where sale.date >= "2020-01-01 00:00:00" and sale.date <= "2020-03-01 00:00:00"
	and sale.status = 1 and customer.cus_name = "jason"

    2. 某商品指定日期內的銷售信息
	select customer.cus_name as cusName, commodity.com_name as comName, sale.quantity, sale.price, measurement.name as measurement, sale.date
    from sale
    left join customer on sale.customer_id = customer.id
    left join commodity on sale.commodity_id = commodity.id
    left join measurement on commodity.measurement = measurement.id
    where sale.date >= "2020-01-01 00:00:00"
    and sale.date <= "2020-03-01 00:00:00"
    and sale.status = 1
    and commodity.com_name = "敌敌畏"

    3. 指定日期內的銷售總信息
	select customer.cus_name as cusName, commodity.com_name as comName, sale.quantity, sale.price, measurement.name as measurement, sale.date
    from sale
    left join customer on sale.customer_id = customer.id
    left join commodity on sale.commodity_id = commodity.id
    left join measurement on commodity.measurement = measurement.id
    where sale.date >= "2020-01-01 00:00:00" and sale.date <= "2020-03-01 00:00:00" and sale.status = 1
     */

    @Query(nativeQuery = true,
            value = "select customer.cus_name as cusName, commodity.com_name as comName, sale.quantity, sale.price, measurement.name as measurement, sale.date " +
                    "from sale " +
                    "left join customer on sale.customer_id = customer.id " +
                    "left join commodity on sale.commodity_id = commodity.id " +
                    "left join measurement on commodity.measurement = measurement.id " +
                    "where sale.date >= ?1 and sale.date <= ?2 and sale.status = 1 and customer.cus_name = ?3 ")
    List<CountInfo> countCustomer(String startDate, String endDate, String cusName);

    @Query(nativeQuery = true,
            value = "select customer.cus_name as cusName, commodity.com_name as comName, sale.quantity, sale.price, measurement.name as measurement, sale.date " +
                    "from sale " +
                    "left join customer on sale.customer_id = customer.id " +
                    "left join commodity on sale.commodity_id = commodity.id " +
                    "left join measurement on commodity.measurement = measurement.id " +
                    "where sale.date >= ?1 and sale.date <= ?2 and sale.status = 1 and commodity.com_name = ?3")
    List<CountInfo> countCommodity(String startDate, String endDate, String comName);

    @Query(nativeQuery = true,
            value = "select customer.cus_name as cusName, commodity.com_name as comName, sale.quantity, sale.price, measurement.name as measurement, sale.date " +
                    "from sale " +
                    "left join customer on sale.customer_id = customer.id " +
                    "left join commodity on sale.commodity_id = commodity.id " +
                    "left join measurement on commodity.measurement = measurement.id " +
                    "where sale.date >= ?1 and sale.date <= ?2 and sale.status = 1")
    List<CountInfo> countDate(String startDate, String endDate);
}
