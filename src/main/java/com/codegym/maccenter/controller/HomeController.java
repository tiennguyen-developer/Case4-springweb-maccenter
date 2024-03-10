package com.codegym.maccenter.controller;

import com.codegym.maccenter.dto.UserDTO;
import com.codegym.maccenter.model.CartItem;
import com.codegym.maccenter.model.Role;
import com.codegym.maccenter.model.User;
import com.codegym.maccenter.service.CategoryService;
import com.codegym.maccenter.service.ProductService;
import com.codegym.maccenter.service.RoleService;
import com.codegym.maccenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model){
        model.addAttribute("cartCount", CartItem.cart.size());
        return "home_template";
    }
    @GetMapping("/home/user/update")
    public String getUser(Model model){
        UserDTO userDTO = new UserDTO();    //principal: (người dùng chính, liên quan bảo mật)
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();   //
        if (principal instanceof UserDetails && ((UserDetails) principal).getUsername() != null) {
            String currentUsername = ((UserDetails)principal).getUsername();
            User user = userService.getUserByEmail(currentUsername).get();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword("");
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            List<Integer> roleIds = new ArrayList<>();
            for (Role item:user.getRoles()) {
                roleIds.add(item.getId());
            }
            userDTO.setRoleList(roleIds);
        }//get current User runtime

        model.addAttribute("userDTO", userDTO);
        return "customer_userUpdate_template";
    }

    @PutMapping("/home/user/update")
    public String updateUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        //convert dto > entity
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        List<Role> roles = userService.getUserById(user.getId()).get().getRoles();
        user.setRoles(roles);

        userService.updateUser(user);
        return "home_template";
    }

    @GetMapping("/shop")
    public String getAllProduct_AllCategory(Model model){
        model.addAttribute("cartCount", CartItem.cart.size());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        return "shop_template";
    }

    @GetMapping("/shop/category/{id}")
    public String getAllProductByCategoryId(@PathVariable int id, Model model){
        model.addAttribute("cartCount", CartItem.cart.size());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProductByCategoryId(id));
        return "shop_template";
    }

    @GetMapping("/shop/product/{id}")
    public String getProductById(@PathVariable long id, Model model){
        model.addAttribute("cartCount", CartItem.cart.size());
        model.addAttribute("product", productService.getProductById(id).get());
        return "customer_productDetail_template";
    }
}
