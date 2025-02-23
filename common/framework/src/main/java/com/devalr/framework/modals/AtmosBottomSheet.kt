package com.devalr.framework.modals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtmosBottomSheet(
    title: String = "",
    onDismiss: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier,
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        AtmosBottomSheetTitle(
            title = title,
            onDismiss = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onDismiss()
                    }
                }
            })
        content(PaddingValues(5.dp))
    }
}

@Composable
fun AtmosBottomSheetTitle(
    title: String = "",
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onDismiss
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.onSurface,
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(5.dp)
        ) {
            AtmosSeparator(size = 7.dp, type = SeparatorType.Vertical)
            AtmosText(
                textAlign = TextAlign.Center,
                text = title,
                type = TextType.Title
            )
        }
    }
}

@Preview
@Composable
private fun AtmosBottomSheetTitlePreview() {
    AtmosynthTheme {
        AtmosBottomSheetTitle(title = "Title", onDismiss = {})
    }
}