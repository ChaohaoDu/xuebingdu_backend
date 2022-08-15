package com.xuebingdu.frontend.dto;

import com.xuebingdu.frontend.entity.Product;
import com.xuebingdu.frontend.entity.ProductSize;
import java.util.List;
import lombok.Data;

@Data
public class NewProduct {
	Product product;
	List<ProductSize> productSize;
}
