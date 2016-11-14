<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="/">
        <Log>
            <date><xsl:value-of select="date" /></date>
            <threadName><xsl:value-of select="threadName" /></threadName>
            <text><xsl:value-of select="text"  /></text>
        </Log>
    </xsl:template>

</xsl:stylesheet>