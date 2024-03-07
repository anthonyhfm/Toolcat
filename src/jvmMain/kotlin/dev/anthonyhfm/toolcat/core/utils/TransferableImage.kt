package dev.anthonyhfm.toolcat.core.utils

import java.awt.Image
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.datatransfer.UnsupportedFlavorException

class TransferableImage(val image: Image) : Transferable {
    override fun getTransferData(flavor: DataFlavor): Any {
        if (!flavor.equals(DataFlavor.imageFlavor)) {
            throw UnsupportedFlavorException(flavor)
        }
        return image
    }

    override fun getTransferDataFlavors(): Array<DataFlavor> = arrayOf(DataFlavor.imageFlavor)
    override fun isDataFlavorSupported(flavor: DataFlavor): Boolean = transferDataFlavors.contains(flavor)
}
