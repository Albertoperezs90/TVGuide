package com.aperezsi.tvguide.data.data

data class APIResponse (val programsResponse: List<ProgramResponse>)
data class ProgramResponse (val genericType: String?,
                            val id: String?,
                            val idChannel: String?,
                            val type: String?,
                            val title: String?,
                            val category: String?,
                            val description: String?,
                            val score: String?,
                            val image: String?,
                            val epochStart: String?,
                            val epochEnd: String?)