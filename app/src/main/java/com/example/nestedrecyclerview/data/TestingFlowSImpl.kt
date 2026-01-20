package com.example.nestedrecyclerview.data

import com.example.nestedrecyclerview.domain.StateFlowState
import com.example.nestedrecyclerview.domain.TestingFlows
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn

class TestingFlowSImpl(val viewModelScope: CoroutineScope) : TestingFlows {
    override fun testStateFlow(): Flow<StateFlowState> {
        return flow {
            emit(StateFlowState.Started)
        }.stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(3000),
            StateFlowState.Started
        )
    }
}

//wh