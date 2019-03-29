<html>
<head>
<style type="text/css">
*{
	font-family: Verdana, calibri;
}

#content{
	margin : auto;
	width: 900px;
}
.boundingBox{	
	text-align:center;
	background:#6666FF;
	padding: 15px 2px 15px 2px;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-box-shadow: 10px 10px 5px #888;
	-webkit-box-shadow: 10px 10px 5px #888;
	box-shadow: 10px 10px 5px #888;	
}
tr .boundingBox {
	width:100px;
}
.boundingBox1{	
	text-align:center;
	background:#6666FF;
	padding: 5px 5px 5px 5px;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-box-shadow: 5px 5px 5px #888;
	-webkit-box-shadow: 5px 5px 5px #888;
	box-shadow: 5px 5px 5px #888;
}
#go{
	clear:both;
	float:right;
	width:200px;
	font-weight:bold;
	color: green;
	-moz-box-shadow: 5px 5px 5px #888;
	-webkit-box-shadow: 5px 5px 5px #888;
	box-shadow: 5px 5px 5px #888;	
}
</style>
<script type="text/javascript">
function getOptions(opt){
	var options = "";
	for(var i = parseInt(opt) ; i <= 100 ; i += 5){
		if(i == 100){	
			options =  options + "<option value='"+i+"' selected='true'>"+i+" C</option>\n";
		}
		else{
			options =  options + "<option value='"+i+"'>"+i+" C</option>\n";
		}
	}
	document.getElementById("end").innerHTML = options;
}
</script>
</head>
<body>
<div id="content">
  <h1 class="boundingBox">Answer The Following Questions</h1><br>

  <form name="quiz" action="quiz.php" method="POST">
  <ol>
  <table>
  <tr><td width=70%>
    <li>
	     Electrostatic interaction is a bonded interaction.
      <ol type="False">
	 <li><input type="radio" name="Q1" value="True">True</li>
	 <li><input type="radio" name="Q1" value="False">False</li>
	 
      </ol>
    </li>
<br>	<?php 
	if($_POST){
		echo "</td><td width=30%>";
		if($_POST['Q1'] == "False"){
			echo "<span style='color:green'>(".$_POST['Q1'].") IS CORRECT</span>";
		}
		else if($_POST['Q1'] == "True"){
			echo "<span style='color:red'>(".$_POST['Q1'].") IS NOT CORRECT !</span>";
		}
		else{
			echo "<span style='color:blue'>NO OPTION SELECTED! </span>";
		}
	}
	?>
  </td></tr>
  <tr><td>
    <li>
	  The electrostatic force between two charged particles is inversely proportional to r<sup>3</sup>?
      <ol type="False">
	 <li><input type="radio" name="Q2" value="True">True</li>
	 <li><input type="radio" name="Q2" value="False">False</li>
	 
      </ol>
    </li><br>
	<?php 
	if($_POST){
		echo "</td><td>";
		if($_POST['Q2'] == "False"){
			echo "<span style='color:green'>(".$_POST['Q2'].") IS CORRECT</span>";
		}
		else if($_POST['Q2'] == "True"){
			echo "<span style='color:red'>(".$_POST['Q2'].") IS NOT CORRECT ! </span>";
		}
		else{
			echo "<span style='color:blue'>NO OPTION SELECTED! </span>";
		}
	}
	?>
  </td></tr>

  <tr><td>
     <li>
	  The  electrostatic potential energy is inversely proprotional to 1/r .
      <ol type="True">
	 <li><input type="radio" name="Q3" value="True">True</li>
	 <li><input type="radio" name="Q3" value="False">False</li>
	 
      </ol>
    </li><br>
	<?php 
	if($_POST){
		echo "</td><td>";
		if($_POST['Q3'] == "True"){
			echo "<span style='color:green'>(".$_POST['Q3'].") IS CORRECT</span>";
		}
		else if($_POST['Q3'] == "False"){
			echo "<span style='color:red'>(".$_POST['Q3'].") IS NOT CORRECT ! </span>";
		}
		else{
			echo "<span style='color:blue'>NO OPTION SELECTED! </span>";
		}
	}
	?>
  </td></tr>

  <tr><td>
    <li>
	      Electrostatic interactions  are longest ranged intermolecular interactions.
      <ol type="True">
	 <li><input type="radio" name="Q4" value="True">True</li>
	 <li><input type="radio" name="Q4" value="False">False</li>
	 
      </ol>
    </li><br>
	<?php 
	if($_POST){
		echo "</td><td>";
		if($_POST['Q4'] == "True"){
			echo "<span style='color:green'>(".$_POST['Q4'].") IS CORRECT</span>";
		}
		else if($_POST['Q4'] == "False"){
			echo "<span style='color:red'>(".$_POST['Q4'].") IS NOT CORRECT !</span>";
		}
		else{
			echo "<span style='color:blue'>NO OPTION SELECTED! </span>";
		}
	}
	?>
  </td></tr>

  <tr><td>
     <li>
	 Electrostatic potential is a vector quantity.
      <ol type="True">
	 <li><input type="radio" name="Q5" value="True">True</li>
	 <li><input type="radio" name="Q5" value="False">False</li>
	 
      </ol>
    </li>
	<?php 
	if($_POST){
		echo "</td><td>";
		if($_POST['Q5'] == "True"){
			echo "<span style='color:green'>(".$_POST['Q5'].") IS CORRECT</span>";
		}
		else if($_POST['Q5'] == "False"){
			echo "<span style='color:red'>(".$_POST['Q5'].") IS NOT CORRECT ! </span>";
		}
		else{
			echo "<span style='color:blue'>NO OPTION SELECTED! </span>";
		}
	}
	?>
  </td></tr>

  
   
</table>
  </ol>
	
	<?php	if(!$_POST){ ?>
	<input type="submit" id="go" class="boundingBox" value="SUBMIT"><br><br><Br>
	<?php }?>
	</form>
<center><button class="boundingBox1" onclick='window.location="./swf.html";'><strong>Back To Experiment<string></button>
<t><button class="boundingBox1" onclick='window.location="./index.html";'><strong>Back To Theory<string></button></center>


</div>
</body>
</html>

