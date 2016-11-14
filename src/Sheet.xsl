<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <body>
                <h1>Company Details</h1>
                <table border="1">
                    <tr>
                        <th>date</th>
                        <th>threadName</th>
                        <th>text</th>
                    </tr>
                    <xsl:for-each select="log">
                        <tr>
                            <td>
                                <xsl:value-of select="date" />
                            </td>
                            <td>
                                <xsl:value-of select="threadName" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>