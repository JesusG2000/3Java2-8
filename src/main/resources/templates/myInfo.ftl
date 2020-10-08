<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page false>
<#--${doctor.name}-->
    <#if isAdmin>
        <#if  !doctor?has_content>
            <form action="/info" method="post" enctype="multipart/form-data">
                <label>Name :
                    <input type="text" name="name">
                </label>
                <label>Surname :
                    <input type="text" name="surname">
                </label>
                <label>Fathername :
                    <input type="text" name="fathername">
                </label>
                <input type="submit" value="Change">
            </form>

        </#if>
        <#if doctor?has_content>
       <form action="/info" method="post" enctype="multipart/form-data">
             <label>Name :
                 <input type="text" name="name" value="${doctor.name}">
             </label>
             <label>Surname :
                 <input type="text" name="surname" value="${doctor.surname}">
             </label>
             <label>Fathername :
                 <input type="text" name="fathername" value="${doctor.fathername}">
             </label>
             <input type="submit" value="Change">
         </form>
        </#if>
    </#if>

    <#if !isAdmin>
        <form action="/info" method="post" enctype="multipart/form-data">
            <label>Name :
                <input type="text" name="name" value="${patient.name}">
            </label>
            <label>Surname :
                <input type="text" name="surname" value="${patient.surname}">
            </label>
            <label>Fathername :
                <input type="text" name="fathername" value="${patient.fathername}">
            </label>
            <input type="submit" value="Change">
        </form>
    </#if>

</@c.page>
