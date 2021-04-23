<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>genes</title>
<script> 
 function sure(){ 	
 	var s="";	
 	var obj=document.getElementById('listGen');
 	var index=obj.selectedIndex; 
 	//var val = obj.options[index].text;
 	for(var i=0;i<obj.options.length;i++){
 		s+=obj.options[i].text+';';
 	}
    window.opener.document.getElementById("leader").innerHTML = s;
    window.opener.document.getElementById("mulGen").value = s;
    window.opener.document.getElementById('gene').setAttribute('readonly','readonly');
    if(s=="" || s==null){
    	window.opener.document.getElementById("mulGen").value = "-1";
    	window.opener.document.getElementById('gene').removeAttribute('readonly');
    }
	window.close();
} 
 function addGen(){
	 var obj=document.getElementById('listGen');	 
	 if(obj.options.length < 5){
		 var gene = document.getElementById('geneName').value;
		 var lg=gene.split(/\n/);
		 if(lg.length<5){
			 for(var i=0;i<lg.length;i++){
				 obj.add(new Option(lg[i],i));
			 }
			 document.getElementById('geneName').value="";
	   }
	 }
 }
 function delGen(){
		 var obj=document.getElementById('listGen');
		 var index=obj.selectedIndex;
		 obj.options.remove(index);
 }
</script>
</head>
<body>
	<%
		String id="";
		if(request.getParameter("id")!=null){
			id = String.valueOf((request.getParameter("id")));
		}
	%>
	<div style="width:160px;margin:auto;">
	<table>
		<tr>
			<td>
				<div style="width:150px;text-align:center;background:#caffff">Gene Symbol</div>
				<select id="listGen" multiple="true" style="width:150px;height:100px;">
					<% String[] strGens = id.split("\\;");
					   int l = strGens.length - 1;
					   if(l>0){
					   for(int i =0;i<l+1;i++){					   
					%>
					<option value=<%=i %>><%=strGens[i] %></option>
					<%}} %>
				</select>
			</td>
		</tr>
		<tr>
			<td style="color:red">Maximum 5 genes are allowed.</td>
		</tr>
		<tr>
			<td style="border:solid 1px #acd6ff;text-align:center">
				<textarea id="geneName" style="width:140px;height:100px"></textarea>
				<div style="height:5px;width:10px;"></div>
				<input type="button" value="add" onclick="addGen();" style="border:solid 1px black" />
				<input type="button" value="delete" onclick="delGen();" style="border:solid 1px black" />				
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center">
				<input type="button" value="ok" onclick="sure();" style="border:solid 1px black" />	
			</td>
		</tr>
	</table>
	</div>
</body>
</html>