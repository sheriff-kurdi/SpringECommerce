package com.kurdi.springecommerce.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryToProductDTOForm {
    List<ProductCategorySellect> productCategoriesList = new ArrayList<>();
}
