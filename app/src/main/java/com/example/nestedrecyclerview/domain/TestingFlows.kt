package com.example.nestedrecyclerview.domain

import kotlinx.coroutines.flow.Flow

sealed class StateFlowState {
    object Started: StateFlowState()
    object Stopped: StateFlowState()
}


interface TestingFlows {
    fun testStateFlow(): Flow<StateFlowState>
}