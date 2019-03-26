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
	     The magnitude of the harmonic force increases linearly with the internuclear separation of a diatomic molecule 
      <ol type="True">
	 <li><input type="radio" name="Q1" id="Q1t" value="True">True</li>
	 <li><input type="radio" name="Q1" id="Q1f" value="False">False</li>
	 <input type="button" value="Clear" onclick="document.getElementById('Q1t').checked=false; document.getElementById('Q1f').checked=false">
      </ol>

    </li>
<br>	<?php 
	if($_POST){
		echo "</td><td width=30%>";
		if($_POST['Q1'] == "True"){
			echo "<span style='color:green'>(".$_POST['Q1'].") IS CORRECT</span>";
		}
		else{
			echo "<span style='color:red'>(".$_POST['Q1'].") IS NOT CORRECT ! </span>";
		}
	}
	?>
  </td></tr>
  <tr><td>
    <li>
	 The curvature of the internuclear potential energy is related to the spring constant
      <ol type="True">
	 <li><input type="radio" name="Q2" id="Q2t" value="True">True</li>
	 <li><input type="radio" name="Q2" id="Q2f" value="False">False</li>
	 <input type="button" value="Clear" onclick="document.getElementById('Q2t').checked=false; document.getElementById('Q2f').checked=false">
      </ol>
    </li><br>
	<?php 
	if($_POST){
		echo "</td><td>";
		if($_POST['Q2'] == "True"){
			echo "<span style='color:green'>(".$_POST['Q2'].") IS CORRECT</span>";
		}
		else{
			echo "<span style='color:red'>(".$_POST['Q2'].") IS NOT CORRECT ! </span>";
		}
	}
	?>
  </td></tr>

  <tr><td>
     <li>
	 The potential energy at large separation's is essentially the bond energy
      <ol type="True">
	 <li><input type="radio" name="Q3" id="Q3t" value="True">True</li>
	 <li><input type="radio" name="Q3" id="Q3f" value="False">False</li>
	 <input type="button" value="Clear" onclick="document.getElementById('Q3t').checked=false; document.getElementById('Q3f').checked=false">
      </ol>
    </li><br>
	<?php 
	if($_POST){
		echo "</td><td>";
		if($_POST['Q3'] == "A"){
			echo "<span style='color:green'>(".$_POST['Q3'].") IS CORRECT</span>";
		}
		else{
			echo "<span style='color:red'>(".$_POST['Q3'].") IS NOT CORRECT ! </span>";
		}
	}
	?>
  </td></tr>

  <tr><td>
    <li>
	      Force is equal to the negative gradient of potential energy
      <ol type="True">
	 <li><input type="radio" name="Q4" id="Q4t" value="True"/>True</li>
	 <li><input type="radio" name="Q4" id="Q4f" value="False">False</li>
	 <input type="button" value="Clear" onclick="document.getElementById('Q4t').checked=false; document.getElementById('Q4f').checked=false">
      </ol>
    </li><br>
	<?php 
	if($_POST){
		echo "</td><td>";
		if($_POST['Q4'] == "True"){
			echo "<span style='color:green'>(".$_POST['Q4'].") IS CORRECT</span>";
		}
		else{
			echo "<span style='color:red'>(".$_POST['Q4'].") IS NOT CORRECT !</span>";
		}
	}
	?>
  </td></tr>

  <tr><td>
     <li>
	 Morse potential is a symmetric function of internuclear separation 
      <ol type="False">
	 <li><input type="radio" name="Q5" id="Q5t" value="True">True</li>
	 <li><input type="radio" name="Q5" id="Q5f" value="False">False</li>
	 <input type="button" value="Clear" onclick="document.getElementById('Q5t').checked=false; document.getElementById('Q5f').checked=false">
      </ol>
    </li>
	<?php 
	if($_POST){
		echo "</td><td>";
		if($_POST['Q5'] == "False"){
			echo "<span style='color:green'>(".$_POST['Q5'].") IS CORRECT</span>";
		}
		else{
			echo "<span style='color:red'>(".$_POST['Q5'].") IS NOT CORRECT ! </span>";
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

