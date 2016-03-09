<%@ page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%-- set data source --%>
<sql:setDataSource driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://cs3.calstatela.edu:3306/cs320stu26" user="cs320stu26"
	password="Ai.dlQHy" />

<%-- query --%>
<sql:query var="results">
 SELECT * FROM items 
 WHERE price > ? AND quantity > ?
 
 <sql:param value="${param.price}" />
	<sql:param value="${param.quantity}" />

</sql:query>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Inventory Search</TITLE>
</HEAD>

<form action="InventorySearch.jsp" method="get">
	Price: <input type="text" name="price" value="${param.price}" /> <br />
	Quantity <input type="text" name="quantity" value="${param.quantity}" />
	<br /> <input type="submit" value="Search" />
</form>

<hr />

<table>
	<tr>
		<c:forEach items="${results.columnNames}" var="column">
			<th><b>${column}</b></th>
		</c:forEach>
	</tr>

	<c:forEach items="${results.rowsByIndex}" var="record">
		<tr>
			<c:forEach items="${record}" var="col">
				<td>${col}</td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>

</BODY>
</HTML>
