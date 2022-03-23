
//package ro.ase.cts.lab02;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // write your code here
        OShop o = new OShop("Emag", "logo.jpg", new ArrayList<Product>());

        Product p1 = new Product("Laptop", 3200.5, 1, 1123);
        Product p2 = new Product("Frigider", 207.95, 2, 1245);

        Product p3 = new Product(p1);

        p3.setQty(5);
        p3.setProdName("Paine");

        o.modifyProductsList(1,p1);
        o.modifyProductsList(1,p2);

        o.modifyProductsList(2, p1);
        System.out.println(p1.equals(p2));
    }
}

class OShop {
    private String onlineShopName;
    private String i;
    private ArrayList<Product> products;

    OShop(String name, String img, ArrayList<Product> products)
    {
        this.products = new ArrayList<Product>();
        this.i = img;
        this.onlineShopName = name;
    }

    public void modifyProductsList(int what, Product p)
    {
        if(what == 1)
        {
            this.products.add(p);
        }
        else
            this.products.remove(p);
    }

    public String getOnlineShopName() {
        return onlineShopName;
    }

    public void setOnlineShopName(String onlineShopName) {
        this.onlineShopName = onlineShopName;
    }

    public String getI() {
        return i;
    }

    public void setImg(String img) {
        this.i = img;
    }
}

class Product//redenumire Product nu e ok sa avbreviem numele clasei
{
    private int prodId;//cls de baza
    private String prodName;//cls de baza
    private double productPrice;//cls de baza
    //private int productType;//se elimina...seamana cu categorie
    private int productCategory; //folosim ENUM, nu tip int
    private int qty;
   // private String exp; //expiry date - only for goods //NU e nevoie in cls de baza(deoarece nu toate produselel au data de exp
    private String details; // ex.: processor, memory, power, volume (for refrigerators etc)

    public Product(String n, double p, int pc, int id)
    {
        prodName = n;
        productPrice = p;
        //productType = pt;
        productCategory = pc;
        this.prodId = id;
       // this.exp = exp;
    }

    //adaugare ENUM
    enum productCategory{

    }

    public Product(Product p)
    {
        this.prodName = p.prodName;
        this.productPrice = p.productPrice;
        //this.productType = p.productType;
        this.productCategory = p.productCategory;
        this.prodId = p.prodId;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

//    public int getProductType() {
//        return productType;
//    }

//    public void setProductType(int productType) {
//        this.productType = productType;
//    }

    public boolean equals(Product p)
    {
        if(p.prodId != this.prodId)
            return false;
        if(!p.prodName.equals(this.prodName))
            return false;
        if(p.productPrice != this.productPrice)
            return false;
//        if(p.productType != p.productType)
//            return false;
        if(p.productCategory != this.productCategory)
            return false;

        return true;
    }

    //spargere in 2 metode

    public void applyDiscount(){}
    public void modify(boolean flag, double val)//spargem in 2 metode= pt reducere si scumpire
    {
        if(flag)
            this.productPrice+=this.productPrice*val;
        if(!flag)
            this.productPrice-=this.productPrice*val/100;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

class User
{
    private String usn;
    private String userId;
    private ArrayList<Order> o;
    private ArrayList<Order> c;

    public User(String usn, String userId)
    {
        this.usn = usn;
        this.userId = userId;
        o=new ArrayList<Order>();
        c=new ArrayList<Order>();
    }

    public void addO1(Order o)
    {
        this.o.add(o);
    }

    public void addO2(Order o)
    {
        this.c.add(o);
    }
}

class Order
{
    private ArrayList<Product> l;
    private String addr;

    public Order()
    {
        l = new ArrayList<Product>();
    }
    public void add(Product p)
    {
        if(l.size() > 99)
            return;

        l.add(p);
    }

    public void remove(Product p)
    {
        l.remove(p);
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}

class InventoryP
{
    private Product p;
    private int q;

    public InventoryP(Product p, int q)
    {
        this.p = new Product(p);
        this.q = q;
    }
    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }
}

//1. Modificare clasa Prod-scoatem datele neimportante din cea de baza
// --- 1.--- creare enum de cateogorie pt a cuprinde toate posibilitatile de produse
// ----------  facem un enum pt fiecare-- goods-categories->ENUM
// -----   punem validari pt atribute id>0, nume>5,cantitate>0 caractere (exceptie personalizata ExceptMinLength)...daca nu se respecta conditia in constr=>returnam NULL
//cand folosim obiectul, verific daca s-a returnat null->nu s-au respectat anumite conditii
//----un singur enum in care sa folosim categorii in functie de fiecare clasa derivata pe care o vom folosi
//2. constructori fara a duplica codul ...pt a nu avea probledme la memoria alocata
//3. spargem metoda in 2 pt reducere(applyDiscount) si scumpire(raisePrice)/ verificam initial sa fie procent


//1.. exceptia personalizata pt verificare nr min 5 caractere