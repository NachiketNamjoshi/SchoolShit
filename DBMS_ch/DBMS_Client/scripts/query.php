<?php



$fname =$_POST['fname'];
$query ="select * from student where name='$fname'";

if(isset($_POST['submit'])) {
	echo SQLResultTable($query);
}


function SQLResultTable($Query)
{	
	$username="root";
	$password="password";
	$database="school";

	$link = mysqli_connect("localhost", $username, $password,$database) or die('Could not connect: '.mysqli_error()); 		//build MySQL Link
	mysqli_select_db($link,$database) or die('Could not select database');		//select database
	$Table = "";  //initialize table variable
	
	$Table.= "<table border='1' style=\"border-collapse: collapse;\">"; //Open HTML Table
	
	$Result = mysqli_query($link,$Query); //Execute the query
	if(mysqli_error($link))
	{
		$Table.= "<tr><td>MySQL ERROR: " . mysqli_error($link) . "</td></tr>";
	}
	else
	{
		//Header Row with Field Names
		$NumFields = mysqli_num_fields($Result);
		$Table.= "<tr style=\"background-color: #fe921f; color: #FFFFFF;\">";
		for ($i=0; $i < $NumFields; $i++)
		{     
			$Table.= "<th>" . mysqli_field_name($Result, $i) . "</th>"; 
		}
		$Table.= "</tr>";
	
		//Loop thru results
		$RowCt = 0; //Row Counter
		while($Row = mysqli_fetch_assoc($Result))
		{
			//Alternate colors for rows
			if($RowCt++ % 2 == 0) $Style = "background-color: #242425; color:#ffffff";
			else $Style = "background-color: #000000; color:#ffffff";
			
			$Table.= "<tr style=\"$Style\">";
			//Loop thru each field
			foreach($Row as $field => $value)
			{
				$Table.= "<td>$value</td>";
			}
			$Table.= "</tr>";
		}
		$Table.= "<tr style=\"background-color: #fe921f; color: #FFFFFF;\"><td colspan='$NumFields'>Query Returned " . mysqli_num_rows($Result) . " records</td></tr>";
	}
	$Table.= "</table>";
	
	return $Table;
}

function mysqli_field_name($result, $field_offset)
{
    $properties = mysqli_fetch_field_direct($result, $field_offset);
    return is_object($properties) ? $properties->name : null;
}
 ?>