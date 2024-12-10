import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mohaberabi.tatbeeq.core.presentation.design.Spacing

import org.jetbrains.compose.resources.painterResource

@Composable
fun ImagePlaceHolder(
    modifier: Modifier = Modifier,
    clipRadius: Dp = Spacing.sm,
    size: Dp = 75.dp
) {

//    Image(
//        painter = painterResource(Res.drawable.img_placholder),
//        contentDescription = "",
//        modifier = modifier
//            .padding(Spacing.sm)
//            .clip(RoundedCornerShape(clipRadius))
//            .size(size)
//    )
}