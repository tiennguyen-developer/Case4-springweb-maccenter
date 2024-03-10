package com.codegym.maccenter.controller;

import com.codegym.maccenter.dto.ProductDTO;
import com.codegym.maccenter.dto.UserDTO;
import com.codegym.maccenter.model.Category;
import com.codegym.maccenter.model.Product;
import com.codegym.maccenter.model.Role;
import com.codegym.maccenter.model.User;
import com.codegym.maccenter.service.CategoryService;
import com.codegym.maccenter.service.ProductService;
import com.codegym.maccenter.service.RoleService;
import com.codegym.maccenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadImage = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/admin")
    public String adminHome() {
        return "adm_manager_template";
    }


    // TÀI KHOẢN =========================================================

    @GetMapping("/admin/users/add")
    public String getFormNewUser(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("roles", roleService.getAllRole());
        return "adm_usersAdd_template";
    }

    @PostMapping("/admin/users/add")
    public String newUser(@ModelAttribute("userDTO") UserDTO userDTO) {

        //convert dto > entity
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        List<Role> roleList = new ArrayList<>();
        for (Integer item : userDTO.getRoleList()) {
            roleList.add(roleService.findRoleById(item).get());
        }
        user.setRoles(roleList);

        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/update/{id}")
    public String updateUser(@PathVariable int id, Model model) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            //convert entity > dto
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword("");
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());

            List<Integer> roleIds = new ArrayList<>();
            for (Role role : user.getRoles()) {
                roleIds.add(role.getId());
            }

            model.addAttribute("userDTO", userDTO);
            model.addAttribute("roles", roleService.getAllRole());
            return "adm_usersUpdate_template";
        } else {
            return "404";
        }
    }

    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users")
    public String getAllUser(Model model) {
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("roles", roleService.getAllRole());
        return "adm_users_template";
    }


    // DANH MỤC =========================================================

    @GetMapping("/admin/categories/add")
    public String getFormNewCategory(Model model) {
        model.addAttribute("category", new Category());
        return "adm_categoriesAdd_template";
    }

    @PostMapping("/admin/categories/add")
    public String newCategory(@ModelAttribute("category") Category category) {
        categoryService.updateCategory(category);
        return "redirect:/admin/categories";
    }


    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "adm_categoriesUpdate_template";
        } else {
            return "404";
        }
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategoryById(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories")
    public String getAllCategory(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "adm_categories_template";
    }


    //SẢN PHẨM =========================================================

    @GetMapping("/admin/products/add")
    public String getFormNewProduct(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "adm_productsAdd_template";
    }

    @PostMapping("/admin/products/add")
    public String newProduct(@ModelAttribute("productDTO") ProductDTO productDTO,   //trích xuất dữ liệu HTML gán biến
                             @RequestParam("productImage") MultipartFile fileProductImage,  //trích xuất file hình ảnh ..
                             @RequestParam("imgName") String imgName) throws IOException { //trích xuất tên hình ảnh ...

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());

        String imageSave;
        if (!fileProductImage.isEmpty()) {
            imageSave = fileProductImage.getOriginalFilename();
            Path fileNameImageAndPath = Paths.get(uploadImage, imageSave);
            Files.write(fileNameImageAndPath, fileProductImage.getBytes());
        } else {
            imageSave = imgName;
        }//save image

        product.setImageName(imageSave);

        productService.updateProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/update/{id}")
    public String updateProductById(@PathVariable long id, Model model) {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setCategoryId(product.getCategory().getId());
            productDTO.setPrice(product.getPrice());
            productDTO.setWeight(product.getWeight());
            productDTO.setDescription(product.getDescription());
            productDTO.setImageName(product.getImageName());

            model.addAttribute("productDTO", productDTO);
            model.addAttribute("categories", categoryService.getAllCategory());
            return "adm_productsUpdate_template";
        } else {
            return "404";
        }
    }

    @GetMapping("/admin/products/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products")
    public String getAllProduct(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "adm_products_template";
    }
}
