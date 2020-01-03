function openMobileSearchBar(){
	if($("#search-bar-mobile").hasClass("hidden")){
		$("#search-bar-mobile").removeClass("hidden");
	}
	
	if(!$("#logo").hasClass("hidden")){
		$("#logo").addClass("hidden")
	}
}

function closeMobileSearchBar(){
	if(!$("#search-bar-mobile").hasClass("hidden")){
		$("#search-bar-mobile").addClass("hidden");
	}
	
	if($("#logo").hasClass("hidden")){
		$("#logo").removeClass("hidden")
	}
}

function search(element){
	console.log($(element).parent().children("input").val());
}

var productsInCart = JSON.parse(localStorage.getItem("productsInCart")) || [];
console.log(productsInCart);

class CartProduct {
  constructor(id, price, quantity) {
    this.id = id;
    this.price = price;
    this.quantity = quantity;
  }
}

function modifyProductList(quantity, id, price){
	
	var found = false;
	
	for(var i=0; i<productsInCart.length; i++){
		if(productsInCart[i].id === id){
			found = true
			
			if(quantity>0){
				productsInCart[i].quantity = quantity; 
				productsInCart[i].price = price;
			}else{
				productsInCart.splice(i, 1)
				break;
			}
		}
	}
	
	if(!found){
		productsInCart.push(new CartProduct(id, price, quantity));
	}
	
	localStorage.setItem("productsInCart", JSON.stringify(productsInCart));
}

// TODO: REFACTOR
function createDelivery(urlSuccess){				
	$.ajax({ 
		url: '/delivery/createDelivery',    
	    type:"POST", 
	    contentType: "application/json; charset=utf-8",
	    data: JSON.stringify(productsInCart), //Stringified Json Object
	    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	    cache: false,    //This will force requested pages not to be cached by the browser          
	    processData:false, //To avoid making query String instead of JSON
	    success: function(){
	        window.location.href = urlSuccess;
    	}
	});
}

function checkout(){				
	$.ajax({ 
		url: '/delivery/createDelivery',    
	    type:"POST", 
	    contentType: "application/json; charset=utf-8",
	    data: JSON.stringify(productsInCart), //Stringified Json Object
	    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	    cache: false,    //This will force requested pages not to be cached by the browser          
	    processData:false, //To avoid making query String instead of JSON
	    success: function(){
	        window.location.href = "/delivery/checkout";
    	}
	});
}

function viewCart(){				
	$.ajax({ 
		url: '/delivery/createDelivery',    
	    type:"POST", 
	    contentType: "application/json; charset=utf-8",
	    data: JSON.stringify(productsInCart), //Stringified Json Object
	    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	    cache: false,    //This will force requested pages not to be cached by the browser          
	    processData:false, //To avoid making query String instead of JSON
	    success: function(){
	        window.location.href = "/user/cart";
    	}
	});
}