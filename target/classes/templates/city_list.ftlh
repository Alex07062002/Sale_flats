<#import "parts/common.ftl" as common>

<@common.page>
    <section>
        <div class="container">
            <table>
                <thead>
                <tr>
                    <th>Город</th>
                </tr>
                </thead>
                <tbody>
                <#list cities as city>
                    <tr onclick="window.location.href='/book/${city.id()}'; return false">
                        <td>${city.name()}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <#include "parts/pagination.ftl">
    </section>
</@common.page>