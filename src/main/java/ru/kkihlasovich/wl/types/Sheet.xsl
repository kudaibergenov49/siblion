<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <body>
                <table>
                    <xsl:for-each select="Output/Log">
                        <log>
                            <date>
                                <xsl:value-of select= "date" />
                            </date>
                            <threadName>
                                <xsl:value-of select="threadName" />
                            </threadName>
                            <text>
                                <xsl:value-of select = "text" />
                            </text>
                        </log>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>