<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>



<@c.page true>
<@l.login "/registration" , "Reg in", true/>

    ${message!}
</@c.page>