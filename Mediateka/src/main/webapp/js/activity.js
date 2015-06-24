function reloadActivity(select){
	
	var period = getSelectedOption(select).value;
	$('#formRecordsRow').load("reloadActivity?period="+period +  ' #formRecords');
}

function getSelectedOption (oListbox)
{

  for (var i=0; i < oListbox.options.length; i++)
  {
      if (oListbox.options[i].selected)  return oListbox.options[i];
  }
  return -1;
};