$(document).ready(function() {
   $(".menuItem").hover(function() {
     $(this).addClass("menuItemHover");
   },function(){
     $(this).removeClass("menuItemHover");
   });

//   $("input[type='text']").hover(function() {
//     $(this).addClass("textHighlight");
//   },function(){
//     $(this).removeClass("textHighlight");
//   });
//   
//   $("input[type='password']").hover(function() {
//     $(this).addClass("textHighlight");
//   },function(){
//     $(this).removeClass("textHighlight");
//   });
//   
//   $("textarea").hover(function() {
//     $(this).addClass("textHighlight");
//   },function(){
//     $(this).removeClass("textHighlight");
//   });

   $(function() {
		$("#datepicker").datepicker({ dateFormat: 'dd/mm/yy' });
		});
   
   $(function() {
		$(".datepicker").datepicker({ dateFormat: 'dd/mm/yy' });
		});
	});

	
	


function EnsureNumeric()
{
  var key = event.keyCode; 
  if (key <48 || key >57) 
    event.returnValue = false; 
}

function CheckReqField(strFormName, strReqParam){
 var blnReturn = true;
 for (var i=0;i<eval(strFormName).length;i++){
  if (eval(strFormName).elements[i].name.substr(0, strReqParam.length) == strReqParam){
   var strTemp = Trim(eval(strFormName).elements[i].value);
   if (strTemp == ''){
    return false;
   }
  }
 }
 
 return true;
}

//This function takes out all the spaces from a string from both sides and the middle and returns the result.
//It is used in conjunction with the above funtion to make sure the user simply didn't put it spaces in the input text field
function Trim(strInput){
 var strTrimmmed = '';
 for (var i = 0;i<strInput.length; i++){
  if (strInput.charCodeAt(i)!=32){
   strTrimmmed += strInput[i];
  }
 }
 return strTrimmmed;
}