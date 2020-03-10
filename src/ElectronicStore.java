//THIS IS THE MODEL
//Class representing an electronic store
//Has an array of products that represent the items the store can sell

import java.util.ArrayList;
import java.util.Arrays;


public class ElectronicStore{
  public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
  private ArrayList<Product> cartList = new ArrayList<>(); //contains all the products in the cart
  private int[] cart; //values represent how many of each product was added to the cart (the indexes are the same as the indexes for the products array)
  private String name; //name of store
  private int sales;
  private double cartValue; //combined total of all prices in the cart
  private double revenue;
  private double dollarsPerSale;
  private Product[] products; //Array to hold all products


  public ElectronicStore(String initName){
    revenue = 0.0;
    sales = 0;
    dollarsPerSale = 0;
    name = initName;
    products = new Product[MAX_PRODUCTS];
    cartList = new ArrayList<>();
    cart = new int[MAX_PRODUCTS];
  }


  /**
   * @param p the product to be added
   * @return true if there's enough space in the products array to add the product, false otherwise
   */
  public boolean addProduct(Product p){
    //add a product p if there's still space in the products array
    for(int i = 0; i < products.length; ++i){
      if(products[i] == null) {
        products[i] = p;
        return true;
      }
    }
    return false;
  }


  /**
   * @param item which product is being sold
   * @param amount how many of that product is being sold
   */
  public void sellProducts(int item, int amount){
    //make sure item is within bounds of products array and products at item is not null
    if(item >= 0 && item <= MAX_PRODUCTS && products[item] != null){
      //add to revenue, (the sell units method also updates the stock of the product)
      this.revenue += products[item].sellUnits(amount);
    }
  }


  /**
   * @param index refers to which product is to be added
   */
  public void addToCart(int index){
      if(index >= 0 && index < MAX_PRODUCTS && products[index] != null){
        cart[index] ++;
        cartList.add(products[index]);
        products[index].setShelfQuantity(products[index].getShelfQuantity() - 1);
        cartValue += products[index].getPrice();
      }
  }


  public void removeFromCart(int productIndex, int listIndex){
    if(productIndex >= 0 && productIndex < MAX_PRODUCTS && products[productIndex] != null){
      cartList.remove(listIndex);
      cart[productIndex] --;
      products[productIndex].setShelfQuantity(products[productIndex].getShelfQuantity() + 1);
      cartValue -= products[productIndex].getPrice();
    }
  }


  public void sellCart(){
    for(int i = 0; i < cart.length; ++i)
      sellProducts(i, cart[i]);
    cartList.clear();
    cartValue = 0.0;
    this.sales++;
    this.dollarsPerSale = revenue/sales;
    //reset array values
    Arrays.fill(cart, 0);
  }

  /**
   *
   * @return an array of the top 3 most sold items
   */
  public ArrayList<Product> getTop3SoldProducts(){
    ArrayList<Product> mostPopular = new ArrayList<>();

    //list that contains indexes of products already declared as popular
    ArrayList<Integer> skip = new ArrayList<>();
    for(int i = 0; i < 3; i++){
      int max = 0;
      int maxIndex = 0;

      for(int j = 0; j < products.length; j++){
        if(products[j] != null && !skip.contains(j)){
          int soldQuantity = products[j].getSoldQuantity();
          if(soldQuantity >= max){
            max = soldQuantity;
            maxIndex = j;
          }
        }
      }

      skip.add(maxIndex);//add the most popular product's index to indexes to skip
      mostPopular.add(products[maxIndex]);
    }
    return mostPopular;
  }
  


  //prints out all the items of the store
  public void printStock(){
    for(int i = 0; i < products.length; ++i){
      if (products[i] == null)
        return;
      System.out.println(i + ". "  + products[i].toString());
    }
  }


  public static ElectronicStore createStore(){
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);
    return store1;
  }

  //getters
  public String getName(){ return name; }
  public int getSales() { return sales; }
  public double getRevenue(){ return revenue; }
  public double getDollarsPerSale(){return dollarsPerSale;}
  public Product[] getProducts() { return products; }
  public ArrayList<Product> getCartList(){return cartList;}
  public double getCartValue(){return this.cartValue;}
} 