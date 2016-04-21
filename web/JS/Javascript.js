function show(element){
   var parentTD = element.parentElement; 
   var parentTR = parentTD.parentElement;
   var siblingTR = parentTR.nextElementSibling;
   
   if (siblingTR.style.display === "none") {
        siblingTR.style.display = "table-row";
   } else {
        siblingTR.style.display = "none";
   }
}