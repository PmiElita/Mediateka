function reloadActivity(){
	
	var period = getSelectedOption(document.getElementById("period")).value;
	var type = getSelectedOption(document.getElementById("recordType")).value
	$('#formRecordsRow').load("reloadActivity?period="+period+"&type="+type +  ' #formRecords');
}

function getSelectedOption (oListbox)
{

  for (var i=0; i < oListbox.options.length; i++)
  {
      if (oListbox.options[i].selected)  return oListbox.options[i];
  }
  return -1;
};