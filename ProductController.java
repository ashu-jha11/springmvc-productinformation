package org.jspiders.springmvcdemo2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    List<Product> productList=new ArrayList<>();

    {
        productList.add(new Product(1,"LAPTOP",45000.25));
        productList.add(new Product(2,"HEADPHONE",5000.25));
        productList.add(new Product(3,"MOBILE",25000.25));
    }
    @GetMapping("/")
    public String getAllProducts(Model model){
        model.addAttribute("records",productList);
        return "productlist";
    }

    @GetMapping("/addproduct")
    public String displayProductForm(Model model){
        model.addAttribute("product",new Product());
        return "productform";
    }

    @PostMapping("/insertproduct")
    public String addProductDetails(Product product){
        productList.add(product);
        return "redirect:/";
    }

    @GetMapping("/updateproduct/{id}")
    public String showUpdateForm(@PathVariable(value = "id") int id,Model model){
        Product p=productList.get(id-1);
        model.addAttribute("product",p);
        return "productupdateform";
    }

    @PostMapping("/modifyproduct")
    public String changeProduct(Product product){
        productList.set(product.getProductId()-1,product);
        return "redirect:/";
    }

    @GetMapping("/deleteproduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") int id, Product product){
        Product p=productList.get(id-1);
        productList.remove(p);
        return "redirect:/";
    }
}
