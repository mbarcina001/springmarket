function openMobileSearchBar(){
	if($("#search-bar-mobile").hasClass("hidden")){
		$("#search-bar-mobile").removeClass("hidden");
	}
	
	if(!$("#logo").hasClass("hidden")){
		$("#logo").addClass("hidden");
	}
}

function closeMobileSearchBar(){
	if(!$("#search-bar-mobile").hasClass("hidden")){
		$("#search-bar-mobile").addClass("hidden");
	}
	
	if($("#logo").hasClass("hidden")){
		$("#logo").removeClass("hidden");
	}
}

function search(element){
	$("#searchTerm").val($(element).parent().children("input").val());
	$("#searchForm").submit();
	
	console.log($(element).parent().children("input").val());
}

$( window ).resize(function() {
	if($( window ).width()>=654){
		if($("#logo").hasClass("hidden")){
			$("#logo").removeClass("hidden")
		}
		closeMobileSearchBar();
	}
});