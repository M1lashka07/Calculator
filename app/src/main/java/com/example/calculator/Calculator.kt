package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState().value
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomEnd = 40.dp,
                        bottomStart = 40.dp
                    )
                )
                .background(MaterialTheme.colorScheme.primaryContainer)
                .weight(1f)
                .padding(bottom = 16.dp, end = 40.dp, start = 40.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            when (state) {
                is CalculatorState.Error -> {
                    Text(
                        text = state.expression,
                        lineHeight = 36.sp,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.error
                    )

                    Text(
                        text = "",
                        fontSize = 17.sp,
                        lineHeight = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                CalculatorState.Initial -> {}
                is CalculatorState.Input -> {
                    Text(
                        text = state.expression,
                        lineHeight = 36.sp,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = state.result,
                        fontSize = 17.sp,
                        lineHeight = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                is CalculatorState.Success -> {
                    Text(
                        text = state.result,
                        lineHeight = 36.sp,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = "",
                        fontSize = 17.sp,
                        lineHeight = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.SQRT))
                    },
                text = "√",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.PI))
                    },
                text = "π",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.POWER))
                    },
                text = "^",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.FACTORIAL))
                    },
                text = "!",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Clear)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AC",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.PARENTHESIS))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "( )",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.PERCENT))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "%",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.DIVIDE))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "÷",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.DIGIT_7))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "7",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.DIGIT_8))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "8",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.DIGIT_9))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "9",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.MULTIPLY))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "X",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.DIGIT_4))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "4",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.DIGIT_5))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "5",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.DIGIT_6))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "6",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.SUBTRACT))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "-",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.DIGIT_1))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "1",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.DIGIT_2))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "2",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.DIGIT_3))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "3",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.ADD))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(2f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.DIGIT_0))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "0",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Input(Symbol.DOT))
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = ",",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f)
                    .clickable {
                        viewModel.processCommandInput(CalculatorCommand.Evaluate)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "=",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}