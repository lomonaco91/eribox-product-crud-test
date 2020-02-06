package inatel.eribox.test.controller;

import inatel.eribox.test.entity.Product;
import inatel.eribox.test.service.impl.ProductServiceImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Scope("view")
public class ProductController implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Atributos Controller">
    private Product product;
    private List products; // Lista de produtos
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Services">
    @Autowired
    private ProductServiceImpl productServiceImpl;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CONSTRUCT METHODS">
    @PostConstruct
    public void init() {
        // Iniciar variaveis utilizadas no JSF
        this.initVariables();
        this.initLists();
    }

    public void initVariables() {
        product = new Product();
    }

    public void initLists() {
        products = productServiceImpl.getAll();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="METHODS FOR CRUD">
    public void saveProduct() {
        if (product.getId() == null) {
            if (productServiceImpl.save(this.product)) {
                products.add(0, product);
                product = new Product(); // resetar formulario

                FacesContext.getCurrentInstance().addMessage("",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Produto inserido com sucesso"));
            } else {
                FacesContext.getCurrentInstance().addMessage("",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Houve falhas durante a inserção."));
            }
        } else {
            if (productServiceImpl.update(this.product)) {
                products = productServiceImpl.getAll();
                
                product = new Product();
                        
                FacesContext.getCurrentInstance().addMessage("",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Produto atualizado com sucesso"));
            } else {
                FacesContext.getCurrentInstance().addMessage("",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Houve falhas durante a atualização."));
            }
        }

    }

    public void initEditProduct(Product product) {
        this.product = product;
    }

    public void removeProduct(Product product) {
        if (productServiceImpl.remove(product.getId(), Product.class)) {
            products.remove(product);

            FacesContext.getCurrentInstance().addMessage("",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Produto removido com sucesso"));
        } else {
            FacesContext.getCurrentInstance().addMessage("",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Houve falhas durante a remoção."));
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List getProducts() {
        return products;
    }

    public void setProducts(List products) {
        this.products = products;
    }
//</editor-fold>
}
