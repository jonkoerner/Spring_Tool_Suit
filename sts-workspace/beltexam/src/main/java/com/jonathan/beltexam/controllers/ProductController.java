package com.jonathan.beltexam.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jonathan.beltexam.models.Product;
import com.jonathan.beltexam.models.User;
import com.jonathan.beltexam.services.ProductService;
import com.jonathan.beltexam.services.UserService;

@Controller
@RequestMapping("/products")
public class ProductController {
	private ProductService pS;
	private UserService uS;
	
	public ProductController(ProductService pS,UserService uS) {
		this.pS = pS;
		this.uS = uS;
	}
	
	@RequestMapping("")
	public String allProducts(Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
			System.out.println((u.getEmail()+ u.getName()));
			model.addAttribute("user",u);
			System.out.println(pS.all());
			// This passes all of products through to my view
			model.addAttribute("products",pS.all());
			return "shoes.jsp";
		}
		return "redirect:/users/new";
	}
	
	@RequestMapping("/dashboard/{user.id}")
	public String dashboard(Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
			System.out.println((u.getEmail()+ u.getName()));
			model.addAttribute("user",u);
			System.out.println(pS.all());
			// This passes all of products through to my view
			model.addAttribute("products",pS.all());
			return "dashboard.jsp";
		}
		return "redirect:/users/new";
	}
	
	
	@RequestMapping("/new")
	public String newProduct(Model model, HttpSession session, @ModelAttribute("product") Product product) {
		if(session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}	
		return "newProduct.jsp";
	}
	
	@PostMapping("/new")
	public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult res,RedirectAttributes flash, HttpSession session) {
		if (res.hasErrors()) {
			flash.addFlashAttribute("errors", res.getAllErrors());
			return "redirect:/courses/new";
		}
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
			
			product.setSeller(u);
			pS.create(product);
			return "redirect:/products";
		}
		
		return "redirect:/new";
	}
	
	
	@RequestMapping("/{id}")
	public String product(@PathVariable("id") Long id, Model model) {
		Optional<Product> product = pS.find(id);
		Product i = product.get();
		
		model.addAttribute("product",i);
		
		return "product.jsp";
	}
	
	@RequestMapping("/{id}/edit")
	public String editProduct(@PathVariable("id")Long id,@ModelAttribute("product")Product product,Model model, HttpSession session) {
		if(session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}else {
			
			Long user_id = (Long) session.getAttribute("id");
			Optional<Product> myProduct = pS.find(id);
			Product i = myProduct.get();
			
			Long creator = i.getSeller().getId();
			if(user_id.equals(creator)) {
				model.addAttribute("product", i);
				return "editProduct.jsp";
			}

			return "redirect:/products";
		}
	}
	
	@PostMapping("/{id}/edit")
	public String updateProduct(@PathVariable("id")Long id,@ModelAttribute("product")Product product, HttpSession session) {
		if(session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}else {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();

			product.setSeller(u);
			pS.update(product);
			return "redirect:/products";

		}
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id")Long id) {
		pS.destroy(id);
		
		return "redirect:/products";
	}		
}
