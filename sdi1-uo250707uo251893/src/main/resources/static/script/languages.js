$(document).ready(function() {
	$("#languageDropdownMenuButton a").click(function(e) {
		e.preventDefault(); // cancel the link behaviour 
		var languageSelectedValue = $(this).attr("value");
		
		window.location.replace('?lang=' + languageSelectedValue);
		return false;
	});
});