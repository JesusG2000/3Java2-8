<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page false>
    <div>
        <h5>All patients of this site</h5>
        <#list users as user>
            <p>${user.username}</p>
            <#if user.filename??>
                <img src="/img/${user.filename}">
            </#if>
        </#list>
    </div>
</@c.page>