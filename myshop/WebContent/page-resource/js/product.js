 function addOne(){
		document.getElementById("purNum").value++;
	}
function deleteOne(){
		var x = document.getElementById("purNum").value;
		if( x >1){
			document.getElementById("purNum").value--;
		}	
	}